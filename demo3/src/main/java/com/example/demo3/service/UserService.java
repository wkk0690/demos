package com.example.demo3.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface UserService extends IService<User> {


    List<User> list1();

    List<User> list2();

    void insert(User user);
}
