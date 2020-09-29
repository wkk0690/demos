package com.alibaba.alibabaclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan({"com.alibaba.alibabaclient.dao"})
public class AlibabaProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaProviderApplication.class, args);
    }

}
