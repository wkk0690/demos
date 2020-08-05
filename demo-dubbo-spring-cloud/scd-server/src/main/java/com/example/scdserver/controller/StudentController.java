package com.example.scdserver.controller;

import com.example.scdapi.api.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wkk
 * @create 2020/08/05
 * @desc
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Reference
    private StudentService studentService;

    @RequestMapping("/test")
    public String test(){
        return studentService.testStudent();
    }
}
