package com.yuxiang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己手写负载均衡模拟轮询
 * 负载均衡算法：rest接口第几次请求数%服务器集群总数=实际调用服务器位置下标，
 * 每次服务重启后rest接口计数从1开始。
 */
@Component
public class MySelfLoadBalance implements LoadBalanced {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);


    private int getInstanceIndex() {
        int current;
        int index;
        do {
            current = atomicInteger.get();
            index = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!atomicInteger.compareAndSet(current, index));
        System.out.println("访问次数统计，当前次数：" + index);
        return index;
    }

    @Override
    public ServiceInstance getInstance(List<ServiceInstance> instances) {
        int instanceIndex = getInstanceIndex() % instances.size();
        return instances.get(instanceIndex);
    }
}
