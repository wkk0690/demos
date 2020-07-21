package com.example.demo.mq.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 顺序消息
 */
public class OrderProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("HAOKE_ORDER_PRODUCER");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            String msgStr = "order --> " + i;
            int orderId = i % 10;
            // 模拟生成订单id
            Message message = new Message("haoke_order_topic", "ORDER_MSG", msgStr.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message,
                    (mqs, msg, arg) -> {
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }, orderId);
            System.out.println(sendResult);
        }
        producer.shutdown();
    }
}