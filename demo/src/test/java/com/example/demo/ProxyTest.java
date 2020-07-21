package com.example.demo;

import com.example.demo.proxy.cglib.CglibProxyFactory;
import com.example.demo.proxy.jdk.JDKProxyFactory;
import com.example.demo.proxy.UserService;
import com.example.demo.proxy.UserServiceImpl;
import org.junit.Test;

/**
 * @author 0X574B4B
 * @create 2018-08-15 14:37
 * @descriptions <p></p >
 */
public class ProxyTest {

    @Test
    public void demo1() {
        UserService userService = new UserServiceImpl();

        JDKProxyFactory proxyFactory = new JDKProxyFactory(userService);
        UserService proxy = (UserService) proxyFactory.createProxy();
        proxy.say();
    }

    @Test
    public void demo2() {
        UserService userService = new UserServiceImpl();

        CglibProxyFactory proxyFactory = new CglibProxyFactory(userService);
        UserService proxy = (UserService) proxyFactory.createProxy();
        proxy.say();
    }
}
