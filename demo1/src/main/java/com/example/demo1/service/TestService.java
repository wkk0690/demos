package com.example.demo1.service;

/**
 * @author 0X574B4B
 * @create 2020/08/18
 * @desc
 */
public interface TestService {

    void methods();

    default void aa(){
        System.out.println("你好世界");
    }

    static void bb(){

    }
}
