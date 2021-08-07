package com.edu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.edu.springcloud.dao")
public class MyBatisConfig {

}
