package com.example.demo1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 0X574B4B
 * @create 2020/08/18
 * @desc
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        var a = new String();



        return "123";
    }
}
