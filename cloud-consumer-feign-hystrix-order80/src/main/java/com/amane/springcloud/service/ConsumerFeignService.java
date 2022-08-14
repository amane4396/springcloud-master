package com.amane.springcloud.service;

import com.amane.springcloud.commons.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE", fallback = ConsumerFeignFallback.class)
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface ConsumerFeignService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String hystrixOk(@PathVariable("id") String id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String hystrixTimeout(@PathVariable("id") String id);

    @GetMapping("/payment/circuit/break/{num}")
    String circuitBreak(@PathVariable("num") Integer number);
}
