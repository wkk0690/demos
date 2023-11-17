package com.example.demo8.controller;

import com.example.demo8.dao.UserDao;
import com.example.demo8.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 隔离级别
 * @author wkk
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/a")
public class IsolateController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test1")
    @Transactional
    public void test1(){
        List<User> all = userDao.getAll();

        System.out.println(11111);

        List<User> all1 = userDao.getAll();

        System.out.println(2222);
    }
}
