package com.example.demo3;

import com.alibaba.fastjson.JSONObject;
import com.example.demo3.config.RedisProperties;
import com.example.demo3.utils.SpringBootBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author wkk
 * @create 2020/07/22
 * @desc
 */
@Component
@Slf4j
public class InitRedisDataSource {

    @Autowired
    private RedisProperties redisProperties;

    public void init(){
        //添加redis
        List<Map<String, String>> list = redisProperties.getList();
        for (Map<String, String> map : list) {
            try {
                LettuceConnectionFactory factory = getFactory(map);
                String name = "redisTemplate" + map.get("database");
                SpringBootBeanUtils.registerBean(name, StringRedisTemplate.class, factory);
                log.info("加载redis" + JSONObject.toJSONString(map) + "成功");
            } catch (Exception e) {
                log.error("加载redis异常", e);
            }

        }
    }

    private LettuceConnectionFactory getFactory(Map map) {
        String host = map.get("host") + "";
        Integer port = Integer.parseInt(map.get("port") + "");
        String password = map.get("password") + "";
        Integer timeout = 3;
        Integer database = Integer.parseInt(map.get("database") + "");
        // 构建工厂对象
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(host);
        configuration.setPort(port);
        configuration.setPassword(RedisPassword.of(password));
        LettucePoolingClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(timeout)).poolConfig(new GenericObjectPoolConfig()).build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(configuration, clientConfiguration);
        // 设置使用的redis数据库
        factory.setDatabase(database);
        // 重新初始化工厂
        factory.afterPropertiesSet();
        return factory;
    }
}
