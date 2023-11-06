package com.example.spark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("wordCount1")
    public Object wordCount1() {
        return "12";
    }
}
