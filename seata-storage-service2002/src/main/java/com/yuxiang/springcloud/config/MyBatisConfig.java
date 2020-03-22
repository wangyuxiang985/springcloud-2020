package com.yuxiang.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yuxiang.springcloud.dao.StorageDao")
public class MyBatisConfig {
}
