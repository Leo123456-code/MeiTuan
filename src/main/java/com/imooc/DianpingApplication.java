package com.imooc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan(basePackages = "com.imooc.dao")
//可以解析Aop的配置
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DianpingApplication {

    public static void main(String[] args) {

        SpringApplication.run(DianpingApplication.class, args);
    }

}
