package com.yuxiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yuxiang.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //如果配置了热点key规则，必须配置blockHandler设置兜底方法，否则返回错误页面
    //SentinelResource处理的是Sentinel控制台配置的违规的情况，由blockHandler方法配置的兜底处理，
    // blockHandler配置的方法对于运行时异常不进行兜底，需要程序中控制
    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "del_hotKey")
    public String hotKey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2) {

        return "hotKey";
    }

    public String del_hotKey(String p1, String p2, BlockException be) {

        return "del_hotKey";
    }
}
