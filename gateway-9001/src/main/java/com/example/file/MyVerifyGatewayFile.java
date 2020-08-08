package com.example.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyVerifyGatewayFile implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("拦截时间: "+new Date());
        String userName = exchange.getRequest().getQueryParams().getFirst("userName");
        /*if(userName == null){
            //如果该参数不存在给拦截
            exchange.getResponse().setStatusCode(HttpStatus.NO_CONTENT);
            return exchange.getResponse().setComplete();
        }*/
        return chain.filter(exchange);
    }

    //执行顺序
    @Override
    public int getOrder() {
        return 0;
    }
}