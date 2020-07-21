package com.example.demo8.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo8.dao.UserDao;
import com.example.demo8.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author wkk
 * @create 2020/06/15
 * @desc
 */
@RestController
@RequestMapping("/user")
@Transactional
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/add")
    public String add(){
        User user = User.builder().username("aa").password("123").id("1").createTime(new Date()).build();
        userDao.add(user);
        return "123";
    }

    @RequestMapping("/edit")
    public String edit(){
        User user = User.builder().username("bb").password("123").id("1").createTime(new Date()).build();
        userDao.edit(user);
        return "123";
    }

    @RequestMapping("/test1")
    public String test1() throws InterruptedException {
        List<User> all = userDao.getAll();
        System.out.println(JSONObject.toJSONString(all));
        Thread.sleep(5000);

        userDao.updatePass();

        List<User> all1 = userDao.getAll1();
        System.out.println(JSONObject.toJSONString(all1));
        return "123";
    }

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        List<User> all = userDao.getAll();
        System.out.println(JSONObject.toJSONString(all));

        User user = User.builder().username("aa").password("123").id("1").createTime(new Date()).build();
        userDao.add(user);

        Thread.sleep(5000);

        List<User> all1 = userDao.getAll1();
        System.out.println(JSONObject.toJSONString(all1));
        return "123";
    }

    @RequestMapping("/getAll")
    public List<User> getAll() throws InterruptedException {
        System.out.println("getAll结果: " + userDao.getAll());

        userDao.updatePass();

        return userDao.getAll();
    }
}
