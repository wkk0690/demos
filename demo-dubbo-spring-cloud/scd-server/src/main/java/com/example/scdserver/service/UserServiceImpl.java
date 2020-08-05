package com.example.scdserver.service;

import com.example.scdapi.api.UserService;
import com.example.scdapi.domain.User;
import com.example.scdserver.dao.UserDao;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wkk
 * @create 2020/08/05
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String test() {
        return userDao.test();
    }
}
