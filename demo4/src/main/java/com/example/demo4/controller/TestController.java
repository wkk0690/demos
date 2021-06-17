package com.example.demo4.controller;

import com.example.demo4.po.Test;
import com.example.demo4.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/add")
    public String add(){
        Test test = new Test();
        test.setId(UUID.randomUUID().toString().replace("-", ""));
        test.setName("jack");
        testService.save(test);
        return "123";
    }

    @RequestMapping("/edit")
    public String edit(){
        Test test = testService.getById("6c7a99fd51d34db4b8d32bf37024bf13");
        test.setName("rose");
        testService.updateById(test);
        return "123";
    }

    @RequestMapping("/edit1")
    public String edit1(){
        Test test = testService.getById("6c7a99fd51d34db4b8d32bf37024bf13");
        test.setName("rose");
        testService.editById(test);
        return "123";
    }
}
