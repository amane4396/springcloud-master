package com.amane.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author xt
 * @date 2022/8/14 14:23:24
 */
@Component
public class ConsumerFeignFallback implements ConsumerFeignService{
    @Override
    public String hystrixOk(String id) {
        return "hystrixOk--fallback" + id;
    }

    @Override
    public String hystrixTimeout(String id) {
        return "hystrixTimeout--fallback" + id;
    }

    @Override
    public String circuitBreak(Integer number) {
        return "80Braek";
    }
}
