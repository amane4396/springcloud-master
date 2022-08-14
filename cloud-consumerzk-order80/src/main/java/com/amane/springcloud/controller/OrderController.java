package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
@RequestMapping("/order")
public class OrderController {
    @Value("${server.port}")
    private String serverPort;

    //cloud-payment-service为zookeeper注册服务名，在8004yaml里面指定

    public static final String URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/zk")
    public String zkp(){
        return restTemplate.getForObject(URL+"/payment/zk",String.class)+"\n"+serverPort;
    }
}
