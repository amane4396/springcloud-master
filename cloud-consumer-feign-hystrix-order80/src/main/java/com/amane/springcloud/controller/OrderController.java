package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.service.ConsumerFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author amane
 * @version 1.0
 * @ClassName OrderController
 * @description
 * @date 2022/8/9 03:47:42
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderController {

    @Resource
    private ConsumerFeignService consumerFeignService;

    @GetMapping("/consumer/hystrix/ok/{id}")
    public String ok(@PathVariable("id") String id){
        return consumerFeignService.hystrixOk(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "timeoutFallback",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2110")
//    })
    @HystrixCommand
    public String timeout(@PathVariable("id") String id){
        return consumerFeignService.hystrixTimeout(id);
    }

    @GetMapping("/consumer/hystrix/circuit/break/{num}")
    public String circuitBreak(@PathVariable("num") Integer number){
        return consumerFeignService.circuitBreak(number);
    }

    public String timeoutFallback(String id){
        return "80timeout系统繁忙"+id;
    }

    public String globalFallback(){
        return "系统繁忙，请稍后再试！";
    }
}
