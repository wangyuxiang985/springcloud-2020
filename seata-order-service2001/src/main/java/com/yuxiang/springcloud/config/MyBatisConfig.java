package com.yuxiang.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yuxiang.springcloud.dao.OrderDao")
public class MyBatisConfig {
}
