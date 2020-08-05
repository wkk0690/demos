package com.example.scdserver.controller;

import com.example.scdserver.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wkk
 * @create 2020/08/05
 * @desc
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/test")
    public String test(){
        return userDao.test();
    }
}
