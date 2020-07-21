package com.example.demo3.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wkk
 * @create 2020/07/20
 * @desc
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        System.out.println(connectionFactory.getDatabase());
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }
    }
}
