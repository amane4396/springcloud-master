package com.amane.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author amane
 * @version 1.0
 * @ClassName OrderMain80
 * @description
 * @date 2022/8/9 03:43:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderCsMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderCsMain80.class, args);
    }
}
