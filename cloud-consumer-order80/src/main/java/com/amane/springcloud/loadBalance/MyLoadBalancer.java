package com.amane.springcloud.loadBalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author amane
 * @version 1.0
 * @ClassName MyLoadBalancer
 * @description
 * @date 2022/8/11 17:38:19
 */
@Component
public class MyLoadBalancer implements IMyLoadBalancer{

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public ServiceInstance choose(List<ServiceInstance> instanceList) {
        int next = getNextIndex(instanceList.size());
        return instanceList.get(next);
    }

    private int getNextIndex(Integer total){
        int next;
        int current = atomicInteger.get();
        do {
            //next = current + 1;
            next = (current + 1) % total;
        }while (!atomicInteger.compareAndSet(current,next));
        return next;
    }
}
