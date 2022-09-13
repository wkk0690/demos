package com.example.scdserver.dao;

import com.alibaba.fastjson.JSONObject;
import com.example.scdapi.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 0X574B4B
 * @create 2020/08/05
 * @desc
 */
@Repository
public class UserDao {

    public String test(int num) {
        List<User> list = new ArrayList<>();
        for(int i = 0; i < num; i ++) {
            list.add(User.builder().id(i + "").username("jack" + i).password(i + "").build());
        }
        return JSONObject.toJSONString(list);
    }
}
