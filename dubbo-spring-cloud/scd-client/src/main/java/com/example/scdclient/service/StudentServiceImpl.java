package com.example.scdclient.service;

import com.example.scdapi.api.StudentService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 0X574B4B
 * @create 2020/08/05
 * @desc
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public String testStudent() {
        return "123";
    }
}
