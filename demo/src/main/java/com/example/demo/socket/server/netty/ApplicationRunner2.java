package com.example.demo.socket.server.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 0X574B4B
 * @create 2018-08-23 16:46
 * @descriptions <p>netty的socket启动执行</p >
 */
@Component
@Order(2)
public class ApplicationRunner2 implements ApplicationRunner {

    @Autowired
    private NettyServer server;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        server.start();
    }
}
