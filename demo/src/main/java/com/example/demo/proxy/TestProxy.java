package com.example.demo.proxy;

import com.example.demo.proxy.cglib.CglibProxyFactory;
import com.example.demo.proxy.jdk.JDKProxyFactory;

/**
 * @author 0X574B4B
 * @create 2020/09/07
 * @desc
 */
public class TestProxy {

    public static void main(String[] args) {

        //jdk动态代理
        UserService userService = new UserServiceImpl();
        userService.say();
        JDKProxyFactory factory = new JDKProxyFactory(userService);
        UserService proxy = (UserService) factory.createProxy();
        proxy.say();

        System.out.println("---------------------------------------");

        //cglib代理
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory(userService);
        UserService proxy1 = (UserService) cglibProxyFactory.createProxy();
        proxy1.say();

    }
}
