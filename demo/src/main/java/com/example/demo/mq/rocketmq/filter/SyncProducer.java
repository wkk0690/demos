package com.example.demo.mq.rocketmq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 同步发送消息
 */
public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.start();

        //发送消息
        String msg = "这是一个用户信息的消息";
        Message message = new Message("my-topic-filter", "*", msg.getBytes("UTF-8"));
        message.putUserProperty("sex", "女");
        message.putUserProperty("age", "17");
        SendResult send = producer.send(message);
        System.out.println("消息id: " + send.getMsgId());
        System.out.println("消息队列: " + send.getMessageQueue());
        System.out.println("消息offset值: " + send.getQueueOffset());
        System.out.println(send);
        producer.shutdown();

        /**
         * 消息id: C0A801320F7818B4AAC28964A9E90000
         * 消息队列: MessageQueue [topic=my-topic, brokerName=broker_haoke_im, queueId=0]
         * 消息offset值: 0
         * SendResult [sendStatus=SEND_OK, msgId=C0A801320F7818B4AAC28964A9E90000,
         * offsetMsgId=7F00000100002A9F0000000000057D64,
         * messageQueue=MessageQueue [topic=my-topic, brokerName=broker_haoke_im, queueId=0], queueOffset=0]
         */
    }
}
