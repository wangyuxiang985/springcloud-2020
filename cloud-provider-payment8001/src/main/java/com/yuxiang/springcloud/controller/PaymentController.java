package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.entities.Payment;
import com.yuxiang.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,服务提供者:" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败，服务提供者:" + serverPort);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        if (result != null) {
            return new CommonResult(200, "查询数据库成功,服务提供者:" + serverPort, result);
        } else {
            return new CommonResult(444, "查询数据库失败,服务提供者:" + serverPort);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****服务实例：" + service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("**每个服务下实例信息：" + "\t" + instance.getServiceId() + "\t" + instance.getHost() + "\t" +
                    instance.getInstanceId() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String selfLB() {
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String feignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


    @GetMapping("/payment/zipkin")
    public String paymentZipKin() {
        return "paymentZipKin ------>" + serverPort;
    }

}
