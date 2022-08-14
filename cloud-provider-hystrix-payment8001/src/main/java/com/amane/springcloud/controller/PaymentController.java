package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.entities.Payment;
import com.amane.springcloud.mapper.PaymentMapper;
import com.amane.springcloud.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author amane
 * @version 1.0
 * @ClassName PaymentController
 * @description
 * @date 2022/8/8 18:50:54
 */
@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private PaymentService paymentService;


    @GetMapping("/hystrix/ok/{id}")
    public String ok(@PathVariable("id") String id){
        return paymentService.getInfoOk(id);
    }

    @GetMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id") String id){
        return paymentService.getInfoTimeOut(id);
    }

    @GetMapping("/circuit/break/{num}")
    public String circuitBreak(@PathVariable("num") Integer num) throws Exception {
        return paymentService.circuitBreakPayment(num);
    }
}
