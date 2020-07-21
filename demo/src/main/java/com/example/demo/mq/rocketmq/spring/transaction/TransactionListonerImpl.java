package com.example.demo.mq.rocketmq.spring.transaction;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;

@RocketMQTransactionListener(txProducerGroup = "myTransactionGroup")
public class TransactionListonerImpl implements RocketMQLocalTransactionListener {

    private static Map<String, RocketMQLocalTransactionState> STATE_MAP = new HashMap<>();

    /**
     * 执行业务逻辑
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        try {
            System.out.println("执行操作1");
            Thread.sleep(500);
            System.out.println("执行操作2");
            Thread.sleep(800);
            STATE_MAP.put(transId, RocketMQLocalTransactionState.ROLLBACK);
            return RocketMQLocalTransactionState.ROLLBACK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        STATE_MAP.put(transId, RocketMQLocalTransactionState.ROLLBACK);
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /**
     * 回查
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        System.out.println("回查消息 -> transId = " + transId + ", state = " + STATE_MAP.get(transId));
        return STATE_MAP.get(transId);
    }
}
