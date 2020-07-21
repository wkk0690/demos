package com.example.demo.socket.client.jdk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * @author 0X574B4B
 * @create 2018-05-24 10:55
 * @desc jdk的websocket启动执行
 */
@Component
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class ApplicationRunner1 implements ApplicationRunner {

    @Autowired
    private WebSocketClient jdkClient;

    @Autowired
    private WebSocketContainer conmtainer;

    @Resource(name = "bianDepthURI")
    private URI bianDepthURI;

    @Resource(name = "bianTradeURI")
    private URI bianTradeURI;

    @Resource(name = "uri4")
    private URI uri4;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        try {

            //启动jdk客户端;连接币安深度
            //conmtainer.connectToServer(jdkClient, bianDepthURI);
            //启动jdk客户端;连接实时成交
            //conmtainer.connectToServer(jdkClient, bianTradeURI);

            //组合流
            //conmtainer.connectToServer(jdkClient, uri4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}