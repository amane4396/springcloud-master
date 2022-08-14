package com.amane.springcloud.service;

import com.amane.springcloud.entities.Payment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author amane
 * @version 1.0
 * @ClassName PaymentService
 * @description
 * @date 2022/8/8 18:16:28
 */
public interface PaymentService extends IService<Payment> {
    String getInfoOk(String id);

    String getInfoTimeOut(String id);

    String getInfoTimeoutFallback(String id);

    String circuitBreakPayment(Integer num) throws Exception;

    String circuitBreakPaymentFallback(Integer id);
}
