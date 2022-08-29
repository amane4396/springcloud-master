package com.amane.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author amane
 * @version 1.0
 * @ClassName Payment8001Application
 * @description
 * @date 2022/8/8 16:56:08
 */
@SpringBootApplication
@EnableEurekaClient
public class GateWay9527Application {
    public static void main(String[] args) {
        SpringApplication.run(GateWay9527Application.class, args);
    }
}