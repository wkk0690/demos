package com.example.demo.mq.rocketmq.transaction;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

public class TransactionProducer {
    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 设置事务监听器
        producer.setTransactionListener(new TransactionListenerImpl());
        producer.start();
        // 发送消息
        Message message = new Message("pay_topic", "用户A给用户B转账1000 元".getBytes("UTF-8"));
        producer.sendMessageInTransaction(message, null);
        Thread.sleep(999999);
        producer.shutdown();
    }
}