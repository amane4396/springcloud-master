package com.amane.springcloud.service;

import com.amane.springcloud.commons.RespBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("CLOUD-PAYMENT-SERVICE")
public interface ConsumerFeignService {

    @GetMapping("/payment/select/{id}")
    public RespBean getPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout")
    public String timeOut();
}
