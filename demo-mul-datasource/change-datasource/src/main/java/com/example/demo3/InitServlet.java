package com.example.demo3;

import com.example.demo3.utils.SpringBootBeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/15.
 * spring容器启动后，初始化数据（产生一个默认商品、普通用户和管理员用户）
 */
@Component
public class InitServlet implements InitializingBean {
 
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("777777777777777777777" + SpringBootBeanUtils.getApplicationContext());

    }
 
}