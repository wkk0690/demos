package com.alibaba.alibabaclient.controller;

import com.alibaba.alibabaclient.dao.TestDao;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProviderController {

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }

    @RequestMapping(value = "/test")
    public String test(@RequestParam("str") String str) {
        return "世界你好 " + str;
    }

    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "世界你好 ";
    }

    @RequestMapping("/findList")
    public String findList() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<Map> list = testDao.findList();
        return JSONObject.toJSONString(list);
    }

    @RequestMapping("/insert")
    @Transactional
    public String insert(){
        Map user = new HashMap();
        user.put("id", UUID.randomUUID().toString().replace("-", ""));
        user.put("username", "jack");
        user.put("password", "123");
        user.put("create_time", new Date());
        testDao.insert(user);
        return "操作成功";
    }

    @RequestMapping("/insert1")
    @Transactional
    public String insert1(){
        Map user = new HashMap();
        user.put("id", UUID.randomUUID().toString().replace("-", ""));
        user.put("username", "rose");
        user.put("password", "123");
        user.put("create_time", new Date());
        //int a = 2/0;
        testDao.insert(user);
        return "操作成功";
    }
}
