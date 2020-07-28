package com.example.scdclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScdClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScdClientApplication.class, args);
    }

}
