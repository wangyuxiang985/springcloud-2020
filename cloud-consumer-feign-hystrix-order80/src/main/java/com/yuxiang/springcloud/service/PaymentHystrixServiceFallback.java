package com.yuxiang.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceFallback implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentHystrixServiceFallback 介入服务降级 paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentHystrixServiceFallback 介入服务降级 paymentInfo_TimeOut";
    }
}
