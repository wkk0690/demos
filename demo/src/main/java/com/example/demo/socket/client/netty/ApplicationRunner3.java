package com.example.demo.socket.client.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 0X574B4B
 * @create 2018-05-24 10:55
 * @descriptions <p>项目启动执行</p >
 */
@Component
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class ApplicationRunner3 implements ApplicationRunner {

    @Autowired
    private WsClient nettyClient;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        try {
            //启动netty客户端;连接火币
//            nettyClient.start();
//            nettyClient.addSub("market.btcusdt.kline.1min","111");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}