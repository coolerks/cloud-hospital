package com.integer.yygh.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class UserGlobalFilter implements GlobalFilter, Ordered {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private static final String LOGIN_ERROR = "{\n" +
            "    \"code\": 208,\n" +
            "    \"message\": \"请登录\"\n" +
            "}";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (antPathMatcher.match("/api/**/auth/**", exchange.getRequest().getURI().getPath())) {
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders()
                    .add("Content-Type", "application/json;charset=UTF-8");
            List<String> tokenList = exchange.getRequest().getHeaders().get("token");
            System.out.println("tokenList = " + tokenList);
            if (tokenList == null || tokenList.size() == 0) {
                DataBuffer dataBuffer = response.bufferFactory()
                        .wrap(LOGIN_ERROR.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(dataBuffer));
            }
        }
//        if (antPathMatcher.match("/api/hosp/hospital/detail/****", exchange.getRequest().getURI().getPath())) {
//            ServerHttpResponse response = exchange.getResponse();
//            response.getHeaders()
//                    .add("Content-Type", "application/json;charset=UTF-8");
//            List<String> tokenList = exchange.getRequest().getHeaders().get("token");
//            System.out.println("tokenList = " + tokenList);
//            if (tokenList == null || tokenList.size() == 0) {
//                DataBuffer dataBuffer = response.bufferFactory()
//                        .wrap(LOGIN_ERROR.getBytes(StandardCharsets.UTF_8));
//                return response.writeWith(Mono.just(dataBuffer));
//            }
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
