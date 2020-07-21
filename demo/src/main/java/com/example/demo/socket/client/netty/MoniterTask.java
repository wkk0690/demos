package com.example.demo.socket.client.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

/**
 * @author 0X574B4B
 * @create 2018/8/14
 * @desc 检测client断开, 并重连的定时器
 */
@Component
public class MoniterTask extends TimerTask {

    private static final Logger logger = LoggerFactory.getLogger(MoniterTask.class);
    private long startTime = System.currentTimeMillis();
    private int checkTime = 5000;

    @Autowired
    public WsClient client;

    public void updateTime() {
        startTime = System.currentTimeMillis();
    }

    public void run() {
        try {
            if (System.currentTimeMillis() - startTime > checkTime) {
                client.setStatus(false);
                logger.error("wsclient reconnect...");
                client.reConnect();
            }
        } catch (Exception e) {
            logger.error("wsclient reconnect error", e);
        }
    }
}