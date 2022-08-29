package com.amane.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xt
 * @date 2022/8/17 04:16:52
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator getRouteLocator(RouteLocatorBuilder builder){
        return builder.routes().route("baidu",r -> r.predicate(p -> p.checkNotModified("asd")).uri("http://www.linuxcool.com/"))
                .build();
    }

}
