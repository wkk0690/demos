package com.example.scdserver;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ScdServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScdServerApplication.class, args);
    }
}
