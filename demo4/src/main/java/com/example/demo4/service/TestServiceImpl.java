package com.example.demo4.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.dao.TestMapper;
import com.example.demo4.po.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void editById(Test test) {
        testMapper.editById(test);
    }
}
