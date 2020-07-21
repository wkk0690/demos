package com.example.demo3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * https://blog.csdn.net/z357904947/article/details/89157281  切换数据源
 *
 * https://blog.csdn.net/czh1924605670/article/details/106999693/ 切换数据源
 *
 * http://go.coder55.com/article/97925 redis数据源切换
 */
@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Demo3Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

}
