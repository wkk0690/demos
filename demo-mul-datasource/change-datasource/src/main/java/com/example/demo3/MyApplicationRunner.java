package com.example.demo3;

import com.example.demo3.config.DatasourcesProperties;
import com.example.demo3.config.DynamicDataSource;
import com.example.demo3.config.RedisProperties;
import com.example.demo3.pojo.DataSource;
import com.example.demo3.utils.SpringBootBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.xml.transform.Source;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author 王凯凯
 * @create 2020-05-24 10:55
 * @descriptions <p>项目启动执行</p >
 */
@Component
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private DatasourcesProperties datasourcesProperties;

    @Override
    public void run(ApplicationArguments applicationArguments) throws InterruptedException {
        for (Map<String, String> map : datasourcesProperties.getList()) {
            try {
                dynamicDataSource.createDataSource(map);
            } catch (Exception e) {
                log.error("创建数据源异常", e);
            }
        }

    }
}