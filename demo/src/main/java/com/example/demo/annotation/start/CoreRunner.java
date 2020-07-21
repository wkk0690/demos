package com.example.demo.annotation.start;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CoreRunner {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Class clazz = Test1.class;
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {

            boolean flag = method.isAnnotationPresent(MyTest.class);
            System.out.println(method.getName() + "-" + flag);
            if(flag) {
                method.invoke(clazz.newInstance(), null);
            }
        }
    }
}
