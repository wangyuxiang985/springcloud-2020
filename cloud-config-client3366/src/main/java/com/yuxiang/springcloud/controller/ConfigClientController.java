package com.yuxiang.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手动版自动刷新读取配置文件，需要运维人员手动发送一个post请求，让3355重新去配置中心读取
 * http://localhost:3355/actuator/refresh
 */
@RestController
@RefreshScope //用户自动刷新配置文件
public class ConfigClientController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return "serverPort:" + serverPort + "\t\t\n configInfo:" + configInfo;
    }
}
