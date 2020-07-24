package com.alibaba.alibabaserver.controller;

import com.alibaba.alibabaserver.api.TestApi;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * https://nacos.io/zh-cn/docs/quick-start.html nacos注册中心, 配置中心, 服务发现
 * https://blog.csdn.net/autfish/article/details/90405679 限流
 * https://blog.csdn.net/autfish/article/details/90411698 熔断
 */
@RestController
@Configuration
@RequestMapping("/server")
public class DemoController {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestApi testApi;

    /**
     * 服务发现
     * @param str
     * @return
     */
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        System.out.println(restTemplate.getForObject("http://alibaba-client/echo/" + str, String.class));
        System.out.println(testApi.refreshToken(str));
        return "成功了";
    }

    /**
     * 分布式事务seata
     * https://segmentfault.com/a/1190000020653512 文章
     * https://github.com/seata/seata-samples 官方demo
     * https://github.com/seata/seata/releases 服务端下载
     */
    @GlobalTransactional(name = "TX_ORDER_CREATE")
    @RequestMapping(value = "/insert")
    public String insert() {
        testApi.insert();
        //int a = 2/0;
        testApi.insert1();
        return "成功了";
    }


    /**
     * sentinel限流
     * https://blog.csdn.net/autfish/article/details/90405679
     [{
         "resource": "protected-resource",
         "controlBehavior": 2,  #QPS流量控制中对超过阈值的流量处理手段 0:直接拒绝 1:Warm Up 2:匀速排队
         "count": 1,            # 限流阈值
         "grade": 1,            # 0基于线程数 1基于QPS
         "limitApp": "default", # 调用来源 default 不区分调用者{some_origin_name} 针对特定的调用者 other 针对除 {some_origin_name} 以外的其余调用方
         "strategy": 0          # 调用关系限流策略 0:根据调用方限流(limitApp) 1:根据调用链路入口限流 2:具有关系的资源流量控制
     }]
     * 在限流设置中，grade=1表示基于QPS/并发数做流量控制，count=1表示阈值为1，即QPS超过1触发。controlBehavior=0表示处理策略是直接拒绝。
     *
     * 降级
     * https://blog.csdn.net/u013792404/article/details/99963582
     *[{
     *     "resource": "protected-resource",
     *     "count": 20.0,
     *     "grade": 0,
     *     "timeWindow": 10
     * }]
     */
    @SentinelResource(value = "protected-resource", blockHandler = "handleBlock")
    @RequestMapping(value = "/test")
    public String test() {
        return testApi.test1();
    }

    public String handleBlock(BlockException e) {
        e.printStackTrace();
        return "请求太快";
    }
}
