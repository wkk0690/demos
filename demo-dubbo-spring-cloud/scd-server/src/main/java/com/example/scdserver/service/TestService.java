package com.example.scdserver.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wkk
 * @create 2020/07/27
 * @desc
 */
@RestController
@Service(version = "1.0.0")
public class TestService implements ITestService {

    @Override
    @GetMapping("/echo")
    public String echo(String msg) {
        System.out.println(msg);
        List<Map> list = new ArrayList<>();
        for(int i = 0; i < 2; i ++) {
            Map map = new HashMap<>();
            map.put("index", i);
            map.put("name", "name" + i);
            map.put("pass", "pass" + i);
            map.put("pass1", "pass1" + i);
            map.put("pass2", "pass2" + i);
            map.put("pass3", "pass3" + i);
            map.put("pass4", "pass3" + i);
            map.put("pass5", "pass3" + i);
            map.put("pass6", "pass3" + i);
            map.put("pass7", "pass3" + i);
            map.put("pass8", "pass3" + i);
            map.put("pass9", "pass3" + i);
            map.put("pass10", "pass3" + i);
            map.put("pass11", "pass3" + i);
            map.put("pass12", "pass3" + i);
            map.put("pass13", "pass3" + i);
            map.put("pass14", "pass3" + i);
            map.put("pass15", "pass3" + i);
            map.put("pass16", "pass3" + i);
            list.add(map);
        }
        return JSONObject.toJSONString(list);
    }
}
