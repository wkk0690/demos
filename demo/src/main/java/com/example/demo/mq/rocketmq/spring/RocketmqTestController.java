package com.example.demo.mq.rocketmq.spring;

import com.example.demo.mq.rocketmq.spring.transaction.SpringTransactionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketmqTestController {

    @Autowired
    private SpringProducer springProducer;
    @Autowired
    private SpringTransactionProducer springTransactionProducer;

    @RequestMapping("/rocketmq")
    public String test(){
        springProducer.sendMsg("spring-my-topic", "我的第一个spring消息");
        return "发送成功";
    }

    @RequestMapping("/txRocketmq")
    public String txRocketmq(){
        springTransactionProducer.sendMsg("spring-tx-my-topic", "我的第3个spring消息");
        return "发送成功";
    }
}
