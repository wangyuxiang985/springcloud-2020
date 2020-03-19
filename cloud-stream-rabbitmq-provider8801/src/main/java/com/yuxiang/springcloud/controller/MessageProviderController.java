package com.yuxiang.springcloud.controller;

import com.yuxiang.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MessageProviderController {

    @Resource
    private IMessageProvider iMessageProvider;

    @GetMapping("sendMessage")
    public String sendMessage() {
        String send = iMessageProvider.send();
        System.out.println("流水号：" + send);
        return send;
    }
}
