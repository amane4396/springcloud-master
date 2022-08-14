package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.entities.Payment;
import com.amane.springcloud.loadBalance.IMyLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.xml.ws.Service;
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
    //public static final String URL = "http://localhost:8001";
    public static final String URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private IMyLoadBalancer loadBalancer;

    @PostMapping("/consumer/payment/create")
    public RespBean create(Payment payment){
        return restTemplate.postForObject(URL+"/payment/create",payment,RespBean.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public RespBean getPayment(@PathVariable("id") Long id){

        return restTemplate.getForObject(URL+"/payment/select/"+id,RespBean.class);
    }

    @GetMapping("/consumer/lbTest/{id}")
    public RespBean getLBPayment(@PathVariable("id") Long id){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBalancer.choose(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/select/"+id, RespBean.class);
    }

    @GetMapping("/consumer/getPort")
    public String getPort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        ServiceInstance instance = loadBalancer.choose(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb/getPort", String.class);

    }
}
