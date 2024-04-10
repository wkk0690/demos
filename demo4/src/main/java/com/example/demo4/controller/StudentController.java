package com.example.demo4.controller;

import com.example.demo4.po.Student;
import com.example.demo4.service.StudentService;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService testService;

    @RequestMapping("/list")
    public List<Student> list(){
        return testService.selectList();
    }

    @RequestMapping("/cursor")
    @Transactional
    public List<Student> cursor(){
        Cursor<Student> students = testService.selectCursorList();
        for (Student student : students) {
            System.out.println(student.toString());
        }
        return null;
    }

    @RequestMapping("/add")
    public String add(){
        Student test = new Student();
        test.setId(UUID.randomUUID().toString().replace("-", ""));
        test.setName("jack");
        testService.save(test);
        return "123";
    }

    @RequestMapping("/edit")
    public String edit(){
        Student test = testService.getById("6c7a99fd51d34db4b8d32bf37024bf13");
        test.setName("rose");
        testService.updateById(test);
        return "123";
    }

    @RequestMapping("/edit1")
    public String edit1(){
        Student test = testService.getById("6c7a99fd51d34db4b8d32bf37024bf13");
        test.setName("rose");
        testService.editById(test);
        return "123";
    }
}
