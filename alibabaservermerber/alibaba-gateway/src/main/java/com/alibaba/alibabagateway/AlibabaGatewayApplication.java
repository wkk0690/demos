package com.alibaba.alibabagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * https://blog.csdn.net/autfish/article/details/90602930
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaGatewayApplication.class, args);
    }

}
