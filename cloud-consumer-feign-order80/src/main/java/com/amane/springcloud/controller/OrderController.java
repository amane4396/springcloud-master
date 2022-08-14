package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.service.ConsumerFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @author amane
 * @version 1.0
 * @ClassName OrderController
 * @description
 * @date 2022/8/9 03:47:42
 */
@RestController
@Slf4j
public class OrderController {

    @Resource
    private ConsumerFeignService consumerFeignService;

    @GetMapping("/consumer/feign/get/{id}")
    public RespBean getPayment(@PathVariable("id") Long id){
        return consumerFeignService.getPayment(id);
    }

    @GetMapping("/consumer/timeout")
    public String timeOut(){
        return consumerFeignService.timeOut();
    }
}
