package com.amane.springcloud.loadBalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author amane
 * @version 1.0
 * @ClassName IMyLoadBalancer
 * @description
 * @date 2022/8/11 17:39:29
 */
public interface IMyLoadBalancer {
    /**
     * 选择service
     *
     * @param instanceList 实例列表
     * @return ServiceInstance
     */
    ServiceInstance choose(List<ServiceInstance> instanceList);
}
