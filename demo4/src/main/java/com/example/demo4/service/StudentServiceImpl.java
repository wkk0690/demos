package com.example.demo4.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.dao.StudentMapper;
import com.example.demo4.po.Student;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private StudentMapper testMapper;

    @Override
    public void editById(Student test) {
        testMapper.editById(test);
    }

    @Override
    public List<Student> selectList() {
        return testMapper.selectList1();
    }

    @Override
    public Cursor<Student> selectCursorList() {
        return testMapper.selectCursorList();
    }
}
