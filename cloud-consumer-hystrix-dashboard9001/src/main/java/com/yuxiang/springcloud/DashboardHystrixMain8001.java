package com.yuxiang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(DashboardHystrixMain8001.class, args);
    }
}
