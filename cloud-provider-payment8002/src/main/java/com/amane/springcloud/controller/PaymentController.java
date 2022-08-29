package com.amane.springcloud.controller;

import com.amane.springcloud.commons.RespBean;
import com.amane.springcloud.commons.RespBeanEnum;
import com.amane.springcloud.entities.Payment;
import com.amane.springcloud.mapper.PaymentMapper;
import com.amane.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.animation.PauseTransition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;

    @Resource
    private PaymentMapper paymentMapper;

    @PostMapping("/create1")
    public RespBean create(@RequestBody String requestBody){
        Payment payment = null;
        try{
            payment = objectMapper.readValue(requestBody,Payment.class);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        if (Objects.isNull(payment)){
            return RespBean.error("无效参数");
        }
        boolean res = paymentService.save(payment);
        if (!res){
            return RespBean.error(40041,"插入失败");
        }
        log.info("********数据插入成功************");
        return RespBean.success(payment);
    }

    @PostMapping("/create")
    public RespBean createe(@RequestBody Payment payment){
        paymentMapper.insert(payment);
        return RespBean.success();
    }

    @GetMapping("/select/{id}")
    public RespBean search(@PathVariable("id") Long id) throws JsonProcessingException {
        Payment payment = paymentService.getById(id);
        if (Objects.isNull(payment)){
            return RespBean.error(40040,"查找失败");
        }

        return RespBean.success("端口号："+serverPort, payment);
    }

    @GetMapping("/lb/port")
    public String getPort(){
        return serverPort;
    }

    @GetMapping("/timeout")
    public String timeOut(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
