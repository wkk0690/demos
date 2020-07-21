package com.example.demo.annotation.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnotationTestController {

    @MyAnnotation("注解值")
    @RequestMapping("/add1")
    public String addData1(String deviceId) {
        System.out.println("=====addData1=====");
        //if(1 == 1) throw new RuntimeException("异常了了了了了");
        return "success";
    }

    @RequestMapping("/add2")
    public String addData2(String deviceId) {
        System.out.println("=====addData2=====");
        if(1 == 1) throw new RuntimeException("异常了了了了了");
        return "success";
    }
}
