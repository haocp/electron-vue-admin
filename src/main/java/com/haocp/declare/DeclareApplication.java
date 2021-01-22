package com.haocp.declare;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 自定义数据源一定要排除SpringBoot自动配置数据源，不然会出现循环引用的问题
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan(basePackages = "com.haocp.declare.web.mapper.*")
public class DeclareApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeclareApplication.class, args);
    }

}
