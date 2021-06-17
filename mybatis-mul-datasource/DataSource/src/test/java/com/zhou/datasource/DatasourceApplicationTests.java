package com.zhou.datasource;

import com.zhou.datasource.annotation.DBChange;
import com.zhou.datasource.dbconfig.DBContextHolder;
import com.zhou.datasource.dbconfig.DynamicDataSource;
import com.zhou.datasource.mapper.DataSourceMapper;
import com.zhou.datasource.mapper.UserMapper;
import com.zhou.datasource.model.DataSource;
import com.zhou.datasource.model.User;
import com.zhou.datasource.service.DBChangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatasourceApplicationTests {
    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DynamicDataSource dynamicDataSource;
    @Autowired
    private DBChangeService dbChangeService;

    /**
     * 测试自动切换
     */
    @Test
    public void contextLoads() {
        //取出数据库中的第一条数据源配置信息
        List<DataSource> list=dataSourceMapper.get();
        DataSource d = list.get(0);
        List<User> list1 = dbChangeService.getUser(d);
        list1.forEach(user -> System.out.println(user.getUserName()));
        /**
         * 第二次查询不用再创建数据源，直接使用
         */
        List<User> list2 = dbChangeService.getUser(d);
        list2.forEach(user -> System.out.println(user.getUserName()));
    }

    /**
     * 手动改变数据源
     */
    @Test
    public void get() throws Exception {
        List<User> list = userMapper.get();
        list.forEach(dataSource1 -> System.out.println(dataSource1.getUserName()));
        System.out.println("---------------------");
        //创建数据源
        DataSource d  = new DataSource();
        d.setDatasourceId("1");
        d.setCode("1");
        d.setUrl("jdbc:mysql://127.0.0.1:3306/test1?characterEncoding=utf8&useSSL=false&serverTimezone=Hongkong&allowPublicKeyRetrieval=true");
        d.setUserName("root");
        d.setPassWord("123456");
        d.setDatabasetype("1");
        dynamicDataSource.createDataSourceWithCheck(d);

        //切换数据源
        DBContextHolder.setDataSource(d.getDatasourceId());


        List<User> list1=userMapper.get();
        list1.forEach(dataSource1 -> System.out.println(dataSource1.getUserName()));
        Thread.sleep(100000);

    }

    /**
     * 手动改变数据源
     */
    @Test
    public void get1() throws Exception {
        List<User> list = userMapper.get();
        list.forEach(dataSource1 -> System.out.println(dataSource1.getUserName()));
    }

}
