package com.example.demo.mq.rocketmq.spring;

import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(
        topic = "spring-my-topic",
        consumerGroup = "spring-consumer-group",
        selectorExpression = "*",
        consumeMode = ConsumeMode.CONCURRENTLY
)
public class SpringConsumer implements RocketMQListener<String> {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String s) {
        System.out.println("接受到消息: " + s);
    }
}
