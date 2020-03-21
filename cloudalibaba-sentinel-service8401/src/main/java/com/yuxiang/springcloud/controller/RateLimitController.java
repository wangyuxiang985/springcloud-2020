package com.yuxiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.entities.Payment;
import com.yuxiang.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rate")
public class RateLimitController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handlerException")//byResource：资源名称 ；handlerException：兜底方法
    public CommonResult byResource() {
        return new CommonResult(200, "按资源名称限流测试OK", new Payment(2020L, "serial2020"));
    }

    public CommonResult handlerException(BlockException bl) {
        return new CommonResult(444, bl.getClass().getCanonicalName() + "\t 服务不可用");
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", //资源名
            blockHandlerClass = CustomerBlockHandler.class, //触发sentinel配置后兜底类
            blockHandler = "customerBlockHandler_2") //兜底方法
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "客户自定义", new Payment(2020L, "serial2020"));
    }
}
