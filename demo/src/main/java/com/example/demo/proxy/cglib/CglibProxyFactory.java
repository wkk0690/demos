package com.example.demo.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 0X574B4B
 * @create 2018-08-15 14:51
 * @descriptions <p>cglib动态代理</p >
 */
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target) {
        this.target = target;
    }

    public Object createProxy() {
        //1:创建enhancer
        Enhancer enhancer = new Enhancer();

        //2:传递class
        enhancer.setSuperclass(target.getClass());

        //3:设置回调操作(相当于invocationHandler)
        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if ("say".equals(method.getName())) {
            System.out.println("增强了................");
        }
        return method.invoke(target, args);
    }
}
