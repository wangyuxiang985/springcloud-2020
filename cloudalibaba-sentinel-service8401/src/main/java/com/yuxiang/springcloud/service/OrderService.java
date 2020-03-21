package com.yuxiang.springcloud.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @SentinelResource("getOrder") //类似于一个资源名
    public void getOrder(){
        System.out.println("orderService/getOrder");
    }
}
