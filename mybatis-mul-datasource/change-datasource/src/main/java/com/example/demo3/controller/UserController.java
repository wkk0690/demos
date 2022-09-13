package com.example.demo3.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.demo3.config.DBContextHolder;
import com.example.demo3.config.DynamicDataSource;
import com.example.demo3.pojo.DataSource;
import com.example.demo3.pojo.User;
import com.example.demo3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 0X574B4B
 * @create 2020/07/20
 * @desc
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    /**
     * mybatisplus 的 @Ds注解
     * @return
     */
    @RequestMapping("/list1")
    public List<User> list1(){
        return userService.list1();
    }

    @RequestMapping("/list2")
    public List<User> list2(){
        return userService.list2();
    }

    /**
     * 改变注解值, 已经加载了没用
     * @return
     * @throws Exception
     */
    @RequestMapping("/test1")
    public List<User> test1() throws Exception {
        Method[] methods = UserService.class.getMethods();
        for (Method method : methods) {
            if("list2".equals(method.getName())) {
                DS ds = method.getAnnotation(DS.class);
                InvocationHandler invocationHandler = Proxy.getInvocationHandler(ds);
                Field f = invocationHandler.getClass().getDeclaredField("memberValues");
                //设置可修改权限
                f.setAccessible(true);
                Map<String, Object> memberValues = (Map<String, Object>) f.get(invocationHandler);
                //获得注解属性~
                String val = (String) memberValues.get("value");
                System.out.println("改变前:" + val);
                if (val.equals("other")) {
                    memberValues.put("value", "master");
                } else {
                    //覆盖之前属性值进行修改
                    memberValues.put("value", "other");
                }
                System.out.println("改变后: " + ds.value());
            }
        }
        return userService.list2();
    }

    @Autowired
    private DynamicDataSource dynamicDataSource;

    /**
     * 切换数据源
     * @return
     * @throws Exception
     */
    @RequestMapping("/test2")
    public List<User> test2() throws Exception {
        List<User> list = userService.list();
        list.forEach(u -> System.out.println(u.getUsername()));
        System.out.println("---------------------");

        //切换数据库test1
        DBContextHolder.setDataSource("1");

        List<User> list1=userService.list();
        list1.forEach(u -> System.out.println(u.getUsername()));

        //动态添加test2, 切换到test2
        changeDatabase();

        List<User> list2=userService.list();
        list2.forEach(u -> System.out.println(u.getUsername()));
        return null;
    }

    /**
     * 事务测试
     * @return
     * @throws Exception
     */
    @RequestMapping("/test3")
    @Transactional
    public List<User> test3() throws Exception {
        User user = User.builder().id("333").username("333").password("333").build();
        userService.insert(user);
        //int a = 2/0;
        User user1 = User.builder().id("444").username("444").password("444").build();
        userService.insert(user1);
        return null;
    }

    /**
     * 线程测试
     * @return
     * @throws Exception
     */
    @RequestMapping("/test4")
    @Transactional
    public List<User> test4() throws Exception {
        final List<User>[] list = new List[]{new ArrayList<>()};
        new Thread(){
            @Override
            public void run() {
                list[0] = userService.list();
            }
        }.run();
        Thread.sleep(3000);
        return list[0];
    }

    private void changeDatabase() throws Exception {
        //创建数据源
        Map map  = new HashMap();
        map.put("datasourceId", "2");
        map.put("url", "jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        map.put("username", "root");
        map.put("password", "123456");

        dynamicDataSource.createDataSource(map);

        //切换数据源
        DBContextHolder.setDataSource(map.get("datasourceId").toString());
    }
}
