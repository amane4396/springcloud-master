package com.amane.rules;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author amane
 * @version 1.0
 * @ClassName MyRule
 * @description
 * @date 2022/8/11 14:26:27
 */
@Configuration
public class MyRule {
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
