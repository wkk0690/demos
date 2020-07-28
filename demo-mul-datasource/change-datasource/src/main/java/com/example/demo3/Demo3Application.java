package com.example.demo3;

import com.example.demo3.utils.SpringBootBeanUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * https://blog.csdn.net/z357904947/article/details/89157281  切换数据源
 * https://github.com/z357904947/DataSource 切换源码
 * https://blog.csdn.net/czh1924605670/article/details/106999693/ 切换数据源
 * https://www.pianshen.com/article/87591242168/ mybatis plus动态数据源切换及查询过程浅析
 * https://www.jianshu.com/p/0f5557fe0d8f mybatis-plus出现Invalid bound statement (not found)
 *
 * http://go.coder55.com/article/97925 redis数据源切换
 */
//@SpringBootApplication
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.example.demo3.dao")
@EnableCaching
public class Demo3Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Demo3Application.class, args);
        SpringBootBeanUtils.setApplicationContext(run);
        InitRedisDataSource initRedisDataSource = run.getBean(InitRedisDataSource.class);
        initRedisDataSource.init();
    }

}
