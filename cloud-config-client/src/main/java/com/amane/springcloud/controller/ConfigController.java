package com.amane.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xt
 * @date 2022/8/30 13:42:06
 */
@RestController
public class ConfigController {

    @Value("config.name")
    private String config;

    @GetMapping("/info")
    public String getInfo(){
        return config;
    }
}
