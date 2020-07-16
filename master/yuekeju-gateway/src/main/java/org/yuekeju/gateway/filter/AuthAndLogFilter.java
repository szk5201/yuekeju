package org.yuekeju.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.util.RedisUtil;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.gateway.feiginservice.UserFeignService;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 2020-7-10
 * @author szk
 * 网关token验证
 */
@Configuration
public class AuthAndLogFilter implements GlobalFilter, Ordered {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private UserFeignService userFeignService;

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -20;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		String method = serverHttpRequest.getMethodValue();
		HttpHeaders headers = serverHttpRequest.getHeaders();
		
		Page<UserEntity> findUser = userFeignService.findUser();
		if (CommonConstants.HTTP_POST.equals(method) || CommonConstants.HTTP_PUT.equals(method) || CommonConstants.HTTP_DEL.equals(method)) {
			Mono<Void> tokenVerfication = postTokenVerfication(serverHttpResponse,redisUtil,headers);
			if(tokenVerfication!=null ){
				return tokenVerfication;
			}
			// 获取webfix 传过来的参数值 并解析
			Mono<Void> flatMap = DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
				byte[] bytes = new byte[dataBuffer.readableByteCount()];
				dataBuffer.read(bytes);
				String bodyString = new String(bytes, StandardCharsets.UTF_8);
				
				exchange.getAttributes().put("POST_BODY", bodyString);
				DataBufferUtils.release(dataBuffer);
				Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
					DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
					return Mono.just(buffer);
				});

				ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
					@Override
					public Flux<DataBuffer> getBody() {
						return cachedFlux;
					}
				};
				return chain.filter(exchange.mutate().request(mutatedRequest).build());
			});
			return flatMap;

		} else if (CommonConstants.HTTP_GET.equals(method)) {
			Map requestQueryParams = serverHttpRequest.getQueryParams();
			// TODO 得到Get请求的请求参数后，做你想做的事
			return chain.filter(exchange);
		} else {
			return  returnDate(false, -2, null, "请求方式错误！", serverHttpResponse);
		}

	}

	public  static Mono<Void> postTokenVerfication(ServerHttpResponse serverHttpResponse,RedisUtil redisUtil, HttpHeaders headers) {
		// 判断解析后是否存在username 和token
		String username = headers.get("username") == null ? null
				: headers.get("username").toString();
		String token = headers.get("token") == null ? null
				:headers.get("token").toString();
		if (username == null || token == null || ("").equals(username) || ("").equals(token)) {
			return returnDate(false, -2, null, "请您重新登录！", serverHttpResponse);
		}

		/*String redisToken = redisUtil.get("username") == null ? null : redisUtil.get("username").toString();
		if (redisToken == null || !(token).equals(redisToken)) {
			return returnDate(false, -1, null, "请您重新登录！", serverHttpResponse);
		}*/
		return null;
	}
	public static Mono<Void> returnDate(Boolean isSuccess,Integer code , String date ,String message, ServerHttpResponse serverHttpResponse){
		ResultVO<String> result =  new ResultVO<String>();
		result.setIsSuccess(isSuccess);
		result.setReturnCode(code);
		result.setReturnDate(date);
		result.setMessage(message);
		StringBuilder logBuilder = new StringBuilder();
		String resp = JSON.toJSONString(result);
		logBuilder.append(",resp=").append(resp);
		DataBuffer bodyDataBuffer = serverHttpResponse.bufferFactory().wrap(resp.getBytes());
		serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
		return serverHttpResponse.writeWith(Mono.just(bodyDataBuffer));
		
	}

}
