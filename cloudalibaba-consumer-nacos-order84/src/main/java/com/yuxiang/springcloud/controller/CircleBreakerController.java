package com.yuxiang.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.entities.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",//资源名
            fallback = "handlerFallback", //服务降级，解决运行时异常兜底
            blockHandler = "blockHandler", //负责sentinel控制台违规配置时兜底，优先级高于fallback
            exceptionsToIgnore = {IllegalArgumentException.class}) //发生指定异常不处理
    public CommonResult<Payment> fallback(@PathVariable("id") Long id){

        CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录，空指针异常");
        }
        return result;
    }

    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底异常handlerFallback，exception内容" + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "blockHandler-sentinel限流，无此流水：blockException" + blockException.getClass().getCanonicalName(), payment);
    }

}
