package org.yuekeju.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
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
import org.yuekeju.common.util.JwtUtil;
import org.yuekeju.common.util.RedisUtil;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;
import org.yuekeju.gateway.feiginservice.UserFeignService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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
		return -20;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		String method = serverHttpRequest.getMethodValue();
		HttpHeaders headers = serverHttpRequest.getHeaders();
		Mono<Void> tokenVerfication = postTokenVerfication(serverHttpResponse, redisUtil, headers);
		if (tokenVerfication != null) {
			return tokenVerfication;
		}
		if (CommonConstants.HTTP_POST.equals(method) || CommonConstants.HTTP_PUT.equals(method) || CommonConstants.HTTP_DEL.equals(method)) {

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
			// 得到Get请求的请求参数后，做你想做的事
			return chain.filter(exchange);
		} else {
			return  returnDate(false, -2, null, "请求方式错误！", serverHttpResponse);
		}

	}

	public  static Mono<Void> postTokenVerfication(ServerHttpResponse serverHttpResponse,RedisUtil redisUtil, HttpHeaders headers) {
		// 判断解析后是否存在username 和token
		String token = headers.get("tokenId") == null ? null
				: headers.get("tokenId").toString();
		if (token == null || ("").equals(token)) {
			return returnDate(CommonConstants.FALSE, ResultEnum.LOGINNANOTTOKEN.getCode(), null, ResultEnum.LOGINNANOTTOKEN.getMessage(), serverHttpResponse);
		}
		try {
			//解析Token
			Claims claims = JwtUtil.parseJWT(token);
			String subject = claims.getSubject();
			UserEntity parse = JSON.parseObject(subject, UserEntity.class);
			final Boolean aBoolean = redisUtil.hashKey(token);
			if (!aBoolean) {
				return returnDate(CommonConstants.FALSE, ResultEnum.LOGINNANOTTOKEN.getCode(), null, ResultEnum.LOGINNANOTTOKEN.getMessage(), serverHttpResponse);
			}
			UserEntity userEntity = JSONObject.parseObject(redisUtil.get(token).toString(), UserEntity.class);
			redisUtil.set(token, userEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return returnDate(CommonConstants.FALSE, ResultEnum.LOGINNAILLEGAL.getCode(), null, ResultEnum.LOGINNAILLEGAL.getMessage(), serverHttpResponse);
		}

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
