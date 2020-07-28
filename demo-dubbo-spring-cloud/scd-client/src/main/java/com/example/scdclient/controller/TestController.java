package com.example.scdclient.controller;

import com.example.scdclient.feign.ScdServerFeign;
import com.example.scdclient.feign.ScdServerFeign1;
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
    @Autowired
    private ScdServerFeign1 scdServerFeign1;

    @RequestMapping("/test")
    public String test(){
        long t1 = System.currentTimeMillis();
        String echo = scdServerFeign.echo("test1");
        long t2 = System.currentTimeMillis();
        return String.format("%s(%s)", "时间", (t2 - t1));
    }

    @RequestMapping("/test1")
    public String test1(){
        long t1 = System.currentTimeMillis();
        String echo = scdServerFeign1.params("test2");
        long t2 = System.currentTimeMillis();
        return String.format("%s(%s)", "时间", (t2 - t1));
    }
}
