package com.example.demo4.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.po.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.ResultSetType;

import java.util.List;

/**
 * @author OX574B4B
 * @create 2021/05/13
 * @desc
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Update("update student set name =#{name} where id=#{id}")
    void editById(Student test);

    List<Student> selectList1();

    @Select("select * from student")
    @Options(resultSetType= ResultSetType.FORWARD_ONLY)
    Cursor<Student> selectCursorList();
}
