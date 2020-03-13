package com.yuxiang.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalanced {

    ServiceInstance getInstance(List<ServiceInstance> instances);
}
