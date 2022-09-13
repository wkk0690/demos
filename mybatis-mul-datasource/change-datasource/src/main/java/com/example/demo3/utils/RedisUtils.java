package com.example.demo3.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 0X574B4B
 * @create 2020/07/20
 * @desc
 */
@Component
public class RedisUtils {

    /*@Resource
    private StringRedisTemplate redisTemplate;

    public void setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        System.out.println(connectionFactory.getDatabase());
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }
    }*/

    public StringRedisTemplate getRedis(String name){
        return SpringBootBeanUtils.getApplicationContext().getBean(name, StringRedisTemplate.class);
    }
}
