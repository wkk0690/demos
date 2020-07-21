package com.example.demo.freemarker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springboot 整合 freemarker 测试
 */
@RequestMapping("/freemarker")
@Controller
public class FreemarkerController {
    @RequestMapping("/test1")
    public String freemarker(Map<String, Object> map) {
        map.put("name", "黑马程序员"); //返回模板文件名称

        List<Map> list = new ArrayList<>();
        Map stu = new HashMap();
        stu.put("name", "jack");
        stu.put("age", "12");
        stu.put("mondy", "mv");

        Map stu1 = new HashMap();
        stu1.put("name", "小明");
        stu1.put("age", "12");
        stu1.put("mondy", "mv");
        list.add(stu);
        list.add(stu1);
        map.put("stus", list);
        return "freemark-test01"; //会默认寻找 resources下的 templates下的 文件
    }
}
