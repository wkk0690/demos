package com.example.demo3.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wkk
 * @create 2020/08/25
 * @desc
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test1(){
        return "123";
    }
}
