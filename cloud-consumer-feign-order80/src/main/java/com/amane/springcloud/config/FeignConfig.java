package com.amane.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author amane
 * @version 1.0
 * @ClassName FeignConfig
 * @description
 * @date 2022/8/13 15:11:36
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
