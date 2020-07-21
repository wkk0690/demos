package com.example.demo.mq.rocketmq.topic;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

public class TopicDemo {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        //设置nameserver
        producer.setNamesrvAddr("127.0.0.1:9876");
        //启动生产者
        producer.start();
        /**
         * 创建topic, 参数分别是broker的名称, topic的名称, queue的数量默认是4
         */
        producer.createTopic("broker_haoke_im", "my-topic", 4);

        System.out.println("topic创建成功");
        producer.shutdown();
    }
}
