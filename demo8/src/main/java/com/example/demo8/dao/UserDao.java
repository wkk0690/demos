package com.example.demo8.dao;

import com.example.demo8.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author wkk
 * @create 2020/06/15
 * @desc
 */
@Mapper
public interface UserDao {

    @Insert("insert into user values (#{id}, #{username}, #{password}, #{createTime})")
    void add(User user);

    @Update("update user set id = #{id}, username = #{username}, password = #{password}, create_time=#{createTime}")
    void edit(User user);

    @Select("select * from user")
    List<User> getAll();

    @Select("select * from user")
    List<User> getAll1();

    @Update("update user set password = '111'")
    void updatePass();
}
