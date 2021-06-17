package com.example.demo4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo4.po.Test;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
public interface TestService extends IService<Test> {

    void editById(Test test);
}
