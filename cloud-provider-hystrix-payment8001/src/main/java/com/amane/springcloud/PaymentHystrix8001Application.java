package com.amane.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
@EnableCircuitBreaker
public class PaymentHystrix8001Application {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrix8001Application.class, args);
    }
}
