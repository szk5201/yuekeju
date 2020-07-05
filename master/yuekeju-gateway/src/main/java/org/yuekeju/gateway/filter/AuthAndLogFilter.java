package org.yuekeju.gateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.yuekeju.common.util.RedisUtil;

import com.alibaba.fastjson.JSON;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class AuthAndLogFilter implements GlobalFilter, Ordered {
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	RestTemplate restTemplate;
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return -20;
	}

	@Value("${spring.redis.host}")
	String host;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// redisTemplate.
		ServerHttpRequest serverHttpRequest = exchange.getRequest();
		ServerHttpResponse serverHttpResponse = exchange.getResponse();
		String method = serverHttpRequest.getMethodValue();
		if ("POST".equals(method)) {
			//获取webfix 传过来的参数值  并解析
			return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
				byte[] bytes = new byte[dataBuffer.readableByteCount()];
				dataBuffer.read(bytes);
				String bodyString = new String(bytes, StandardCharsets.UTF_8);
				//判断解析后的值是否为空
				if (bodyString == null || ("").equals(bodyString)) {
					StringBuilder logBuilder = new StringBuilder();
					Map map = new HashMap<>();
					map.put("code", 1);
					map.put("message", "请求参数不能为空！");
					String resp = JSON.toJSONString(map);
					logBuilder.append(",resp=").append(resp);
					DataBuffer bodyDataBuffer = serverHttpResponse.bufferFactory().wrap(resp.getBytes());
					serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
					return serverHttpResponse.writeWith(Mono.just(bodyDataBuffer));
				}
				//判断解析后是否存在username 和token 
				String username = JSON.parseObject(bodyString).get("username") == null ? null
						: JSON.parseObject(bodyString).get("username").toString();
				String token = JSON.parseObject(bodyString).get("token") == null ? null
						: JSON.parseObject(bodyString).get("token").toString();
				if (username == null || token == null || ("").equals(username) || ("").equals(token)) {
					StringBuilder logBuilder = new StringBuilder();
					Map map = new HashMap<>();
					map.put("code", -1);
					map.put("message", "数据不能为空！");
					String resp = JSON.toJSONString(map);
					logBuilder.append(",resp=").append(resp);
					DataBuffer bodyDataBuffer = serverHttpResponse.bufferFactory().wrap(resp.getBytes());
					serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
					return serverHttpResponse.writeWith(Mono.just(bodyDataBuffer));
				}
				
				String redisToken = redisUtil.get("username")==null ? null : redisUtil.get("username").toString() ;
				if(redisToken == null ||  !(token).equals(redisToken)){
					StringBuilder logBuilder = new StringBuilder();
					Map map = new HashMap<>();
					map.put("code", -1);
					map.put("message", "请您重新登录！");
					String resp = JSON.toJSONString(map);
					logBuilder.append(",resp=").append(resp);
					DataBuffer bodyDataBuffer = serverHttpResponse.bufferFactory().wrap(resp.getBytes());
					serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
					return serverHttpResponse.writeWith(Mono.just(bodyDataBuffer));
				}
				
				
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

		} else if ("GET".equals(method)) {
			Map requestQueryParams = serverHttpRequest.getQueryParams();
			// TODO 得到Get请求的请求参数后，做你想做的事
			return chain.filter(exchange);
		} else {
			StringBuilder logBuilder = new StringBuilder();
			Map map = new HashMap<>();
			map.put("code", 1);
			map.put("message", "请求方式错误");
			String resp = JSON.toJSONString(map);
			logBuilder.append(",resp=").append(resp);
			DataBuffer bodyDataBuffer = serverHttpResponse.bufferFactory().wrap(resp.getBytes());
			serverHttpResponse.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
			return serverHttpResponse.writeWith(Mono.just(bodyDataBuffer));
		}

	}
}
