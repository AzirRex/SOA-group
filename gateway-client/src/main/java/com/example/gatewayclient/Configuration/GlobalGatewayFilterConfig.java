package com.example.gatewayclient.Configuration;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalGatewayFilterConfig {
    @Bean
    @Order(-100)
    public GlobalFilter elapsedGlobalFilter() {
        return (exchange, chain) -> {
            Long startTime = System.currentTimeMillis();//获取当前时间
            return chain.filter(exchange).then().then(Mono.fromRunnable(()->{
                Long endTime = System.currentTimeMillis();
                System.out.println(exchange.getRequest().getURI().getRawPath()+" cost time:"+(endTime-startTime)+"ms");
            }));
        };
    }

}
