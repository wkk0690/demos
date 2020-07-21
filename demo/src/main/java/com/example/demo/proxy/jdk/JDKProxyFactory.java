package com.example.demo.proxy.jdk;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 0X574B4B
 * @create 2018-08-15 14:33
 * @descriptions <p>jdk的动态代理</p >
 */
public class JDKProxyFactory implements InvocationHandler {

    private Object target;

    public JDKProxyFactory(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        ClassLoader classLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("say")) {
            System.out.println("增强say");

        }
        return method.invoke(target, args);
    }
}


