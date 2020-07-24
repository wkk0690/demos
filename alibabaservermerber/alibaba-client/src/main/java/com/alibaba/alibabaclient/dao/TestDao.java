package com.alibaba.alibabaclient.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface TestDao {

    @Select("select * from user")
    List<Map> findList();

    @Insert("insert into user (id, username, password, create_time) values (#{id}, #{username}, #{password}, #{create_time})")
    void insert(Map user);
}
