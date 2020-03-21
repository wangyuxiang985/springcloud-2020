package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

    @Resource
    private OrderService orderService;

    @GetMapping("/testA")
    public String testA() {
        orderService.getOrder();
        return "------------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        orderService.getOrder();
        return "------------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(2L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "------------testD,RT";
    }

    @GetMapping("/testE")
    public String testE() {
        int i = 10 / 0;
        return "------------testE,异常比例数";
    }
}
