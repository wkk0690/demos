package com.example.demo3.controller;

import com.example.demo3.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wkk
 * @create 2020/07/20
 * @desc
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/get")
    public String get(Integer db){
//        redisUtils.setDataBase(db);

        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory != null && db != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(db);
            redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }

        return redisTemplate.opsForValue().get("aa") + "";
    }
}
