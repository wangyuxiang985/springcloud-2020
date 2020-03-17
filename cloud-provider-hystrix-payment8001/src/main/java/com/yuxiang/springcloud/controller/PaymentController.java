package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String s = paymentHystrixService.paymentInfo_OK(id);
        log.info("调用ok方法了:{}", s);
        return s;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String s = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("调用timeout方法了:{}", s);
        return s;
    }

    @GetMapping("/payment/hystrix/breaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String s = paymentHystrixService.paymentCircuitBreaker(id);
        log.info("调用timeout方法了:{}", s);
        return s;
    }
}
