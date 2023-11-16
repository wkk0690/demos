package com.example.demo.remoteDebug;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://jingyan.baidu.com/article/925f8cb8ef722181dde0569f.html
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    /**
     * java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8081 -jar demo2-0.0.1-SNAPSHOT.jar
     * 右键debug的点可以选择Thread不卡其它线程
     * @return
     */
    @RequestMapping("/a")
    public String demo1() {
        log.error("11111111111");
        System.out.println("111111111");
        log.error("22222222");
        System.out.println("22222222");
        return "111";
    }

    @RequestMapping("/b")
    public String demo2() {
        return "222";
    }
}
