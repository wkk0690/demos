package com.example.hbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://archive.apache.org/dist/hbase/ 下载
 * https://www.yisu.com/zixun/620547.html 安装hbase
 * http://192.168.220.147:16010/
 */
@SpringBootApplication
public class HbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbaseApplication.class, args);
    }

}
