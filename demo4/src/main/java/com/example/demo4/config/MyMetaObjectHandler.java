package com.example.demo4.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 字段填充审计
 * @author Exrick
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        setFieldUser("createBy", metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        setFieldUser("updateBy", metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    public void setFieldUser(String field, MetaObject metaObject) {
        this.setFieldValByName(field, "jack", metaObject);
    }
}

