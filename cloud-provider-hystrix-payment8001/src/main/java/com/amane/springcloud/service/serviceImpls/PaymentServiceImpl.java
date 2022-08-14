package com.amane.springcloud.service.serviceImpls;

import com.amane.springcloud.entities.Payment;
import com.amane.springcloud.mapper.PaymentMapper;
import com.amane.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author amane
 * @version 1.0
 * @ClassName PaymentServiceImpl
 * @description
 * @date 2022/8/8 18:18:01
 */
@Service
@Slf4j
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>implements PaymentService{

    @Value("${server.port}")
    private String serverPort;


    @Override
    public String getInfoOk(String id) {

        return "**************调用成功"+"\t"+"线程池："+"调用成功"+Thread.currentThread().getName()+" id:"+id +" port:"+serverPort;
    }

    @Override
    @HystrixCommand(fallbackMethod = "getInfoTimeoutFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"
            )})
    public String getInfoTimeOut(String id) {
        int seconds = 4;
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "**************调用成功"+"\t"+"线程池："+Thread.currentThread().getName()+" port:"+serverPort+" id:"+id+" 耗时："+seconds+"s";
    }

    @Override
    public String getInfoTimeoutFallback(String id) {
        return serverPort + "系统繁忙" + id;
    }

    @HystrixCommand(fallbackMethod = "circuitBreakPaymentFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "70")
    })
    @Override
    public String circuitBreakPayment(Integer number) {
        if (number < 0){
            throw new RuntimeException("数字不可小于0");
        }
        String uuid = UUID.randomUUID().toString();
        String result = number+"  "+uuid;
        log.info(result);
        return result;
    }

    @Override
    public String circuitBreakPaymentFallback(Integer id) {
        return "FALLBACK"+id;
    }
}
