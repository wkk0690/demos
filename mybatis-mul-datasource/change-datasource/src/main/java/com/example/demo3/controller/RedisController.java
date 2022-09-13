package com.example.demo3.controller;

import com.example.demo3.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 0X574B4B
 * @create 2020/07/20
 * @desc
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisTemplate redisTemplate1;
    @Resource
    private StringRedisTemplate redisTemplate2;
    @Resource
    private StringRedisTemplate redisTemplate3;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/test")
    public String test(Integer db){

//        redisTemplate.opsForValue().set("bb", "22222");
//        redisTemplate1.opsForValue().set("bb", "22222");

//        System.out.println(redisTemplate2.opsForValue().get("bb"));
//        System.out.println(redisTemplate3.opsForValue().get("bb"));

        System.out.println(redisUtils.getRedis("redisTemplate" + db).opsForValue().get("bb"));

//        changeRedis(db);
//
//        redisTemplate.opsForValue().set("bb", "22222");

        return redisTemplate.opsForValue().get("aa") + "";
    }

    @RequestMapping("/test1")
    public String test1(Integer db){
//        redisUtils.setDataBase(db);
        return redisTemplate.opsForValue().get("aa") + "";
    }

    private void changeRedis(Integer db) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
        if (connectionFactory != null && db != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(db);
            redisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }
    }
}
