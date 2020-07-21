package com.example.demo8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://127.0.0.1:8088/druid/sql.html druid监控页面
 */
@SpringBootApplication
public class Demo8Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo8Application.class, args);
    }

}
