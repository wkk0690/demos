package com.example.demo.mq.rocketmq.spring;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     * @param topic
     * @param msg
     */
    public void sendMsg(String topic, String msg){
        this.rocketMQTemplate.convertAndSend(topic, msg);
    }
}
