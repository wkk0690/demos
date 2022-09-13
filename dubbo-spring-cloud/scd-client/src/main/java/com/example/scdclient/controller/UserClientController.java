package com.example.scdclient.controller;

import com.example.scdapi.api.UserService;
import com.example.scdclient.feign.ScdServerFeign;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 0X574B4B
 * @create 2020/07/27
 * @desc 50万3个字段差1s, 10万3字段差200毫秒
 */
@RestController
@RequestMapping("/user")
public class UserClientController {

    @Autowired
    private ScdServerFeign scdServerFeign;

    @Reference
    private UserService userService;

    /**
     * feign调用
     * @return
     */
    @RequestMapping("/test")
    public String test(@RequestParam(defaultValue = "10000") int num){
        long t1 = System.currentTimeMillis();
        String feignReuslt = scdServerFeign.test(num);
        //System.out.println(feignReuslt);
        long t2 = System.currentTimeMillis();
        long feignTime = t2 - t1;

        long t3 = System.currentTimeMillis();
        String dubboResult = userService.test(num);
        //System.out.println(dubboResult);
        long t4 = System.currentTimeMillis();
        long dubboTime = t4 - t3;
        return String.format("feign-dubbo时间差: %s", (feignTime - dubboTime));
    }
}
