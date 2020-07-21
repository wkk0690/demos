package com.example.demo3.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo3.dao.UserDao;
import com.example.demo3.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wkk
 * @create 2020/07/20
 * @desc
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> list() {
        return userDao.list();
    }

    @DS("master")
    public List<User> list1() {
        return userDao.list1();
    }

    @DS("other")
    public List<User> list2() {
        return userDao.list2();
    }

    public void insert(User user) {
        userDao.insert(user);
    }
}
