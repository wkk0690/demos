package com.example.oracle.controller;

import com.example.oracle.entity.User;
import com.example.oracle.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TestController
 *
 * @author wkk
 * @date 2023-11-17
 */
@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    @Transactional
    public Object get() {
        List<User> all = userMapper.findAll();
        System.out.println(1111);
        List<User> all1 = userMapper.findAll();
        System.out.println(2222);

        return "123";
    }
}
