package com.example.demo4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo4.po.Student;

import java.util.List;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
public interface StudentService extends IService<Student> {

    void editById(Student test);

    List<Student> selectList();
}
