package com.example.demo.mq.rocketmq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class ConsumerDemo {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("haoke-consumer");
        consumer.setNamesrvAddr("127.0.0.1:9876");

        /**
         * 订阅消息, 接受所有消息("*")
         * 订阅消息, 接受 tags add的 是 ("add")
         * 订阅消息, 接受 tags add 或者 update的 是 ("add || update")
         */
        consumer.subscribe("my-topic-filter", MessageSelector.bySql("sex = '女' and age = '18'"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                try {
                    for (MessageExt msg : list) {
                        System.out.println("消息: " + new String(msg.getBody(), "UTF-8"));
                    }
                    System.out.println("接收到消息了: " + list);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        //启动消费
        consumer.start();
    }
}
