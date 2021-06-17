package com.example.demo4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.po.Test;
import org.apache.ibatis.annotations.Update;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
public interface TestMapper extends BaseMapper<Test> {

    @Update("update student set name =#{name} where id=#{id}")
    void editById(Test test);
}
