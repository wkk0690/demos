package com.example.demo3.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo3.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wkk
 * @create 2020/07/20
 * @desc
 */
public interface UserDao extends BaseMapper<User> {

    @Select("select * from user")
    List<User> list();

    @Select("select * from user")
    List<User> list1();

    @Select("select * from user")
    List<User> list2();

    @Insert("insert into user values (#{id}, #{username}, #{password}, now())")
    int insert(User user);
}
