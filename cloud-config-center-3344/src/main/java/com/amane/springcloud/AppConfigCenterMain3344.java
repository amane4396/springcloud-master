package com.amane.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author amane
 * @version 1.0
 * @ClassName OrderMain80
 * @description
 * @date 2022/8/9 03:43:29
 */
@SpringBootApplication
@EnableConfigServer
public class AppConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigCenterMain3344.class, args);
    }
}
