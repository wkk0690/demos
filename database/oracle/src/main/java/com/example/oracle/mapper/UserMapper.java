package com.example.oracle.mapper;

import com.example.oracle.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * UserMapper
 *
 * @author wkk
 * @date 2023-11-17
 */
@Mapper
public interface UserMapper {

    @Select("select * from T_USER")
    public List<User> findAll();
}
