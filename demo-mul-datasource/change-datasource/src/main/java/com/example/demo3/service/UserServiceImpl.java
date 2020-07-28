package com.example.demo3.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo3.dao.UserDao;
import com.example.demo3.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wkk
 * @create 2020/07/22
 * @desc
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

//    @DS("master")
    public List<User> list1() {
        return userDao.list1();
    }

//    @DS("other")
    public List<User> list2() {
        return userDao.list2();
    }

    public void insert(User user) {
        userDao.insert(user);
    }
}
