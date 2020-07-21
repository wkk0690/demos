package com.example.demo.mq.rocketmq.sendmsg;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * 异步发送消息
 */
public class AsyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("haoke");

        producer.start();

        Message message = new Message("my-topic", "我的第一个异步发送".getBytes("UTF-8"));
        producer.send(message, new SendCallback() {
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送成功了" + sendResult);
                System.out.println("消息id: " + sendResult.getMsgId());
                System.out.println("消息队列: " + sendResult.getMessageQueue());
                System.out.println("消息offset值: " + sendResult.getQueueOffset());
            }

            public void onException(Throwable throwable) {
                System.out.println("消息发送失败" + throwable);
            }
        });

        //producer.shutdown();
    }
}
