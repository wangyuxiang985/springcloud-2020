package com.yuxiang.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate 由spring提供的封装过后的httpclient，简单方便的请求Restful形式的接口
     * 使用@LoadBalanced注解，让RestTemplate具有负载均衡的能力，否则消费方不知道有几个提供者，会出现报错
     * @return RestTemplate
     */
    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
