package com.example.demo3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisProperties {

    private List<Map<String, String>> list;
}