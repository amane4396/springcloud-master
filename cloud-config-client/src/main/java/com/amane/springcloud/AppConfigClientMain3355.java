package com.amane.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author amane
 * @version 1.0
 * @ClassName OrderMain80
 * @description
 * @date 2022/8/9 03:43:29
 */
@SpringBootApplication
@EnableEurekaClient
public class AppConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigClientMain3355.class, args);
    }
}
