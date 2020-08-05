package com.example.scdclient.controller;

import com.example.scdapi.api.UserService;
import com.example.scdclient.feign.ScdServerFeign;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wkk
 * @create 2020/07/27
 * @desc
 */
@RestController
public class TestController {

    @Autowired
    private ScdServerFeign scdServerFeign;

    @Reference
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        long t1 = System.currentTimeMillis();
        String test = scdServerFeign.test();
        System.out.println(test);
        long t2 = System.currentTimeMillis();
        return String.format("%s(%s)", "时间", (t2 - t1));
    }

    @RequestMapping("/test1")
    public String test1(){
        long t1 = System.currentTimeMillis();
        String test = userService.test();
        System.out.println(test);
        long t2 = System.currentTimeMillis();
        return String.format("%s(%s)", "时间", (t2 - t1));
    }
}
