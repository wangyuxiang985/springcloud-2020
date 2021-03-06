package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String feignTimeout() {
        //OpenFeign默认等待1S钟，超时报错
        return paymentFeignService.feignTimeout();
    }
}
