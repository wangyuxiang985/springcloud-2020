package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.entities.CommonResult;
import com.yuxiang.springcloud.entities.Payment;
import com.yuxiang.springcloud.lb.LoadBalanced;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {

    //单机情况下可以直接写死IP:port
//    private final static String PAYMENT_URL = "http://localhost:8001";
    //服务提供者集群模式下，应该写服务提供方，服务名
    private final static String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalanced loadBalanced;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/consumer/payment/lb")
    public String getLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (CollectionUtils.isEmpty(instances)) {
            return "没有服务实例";
        }
        ServiceInstance instance = loadBalanced.getInstance(instances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri + "/payment/lb/" , String.class);
    }

    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipKin() {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
    }
}
