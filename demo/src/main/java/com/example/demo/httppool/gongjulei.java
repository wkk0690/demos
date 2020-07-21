package com.example.demo.httppool;

/**
 * @author 0X574B4B
 * @create 2018-08-22 9:27
 * @descriptions <p></p >
 */
public class gongjulei {

    static {
        System.out.println("静态代码块");
    }

    public gongjulei() {
        System.out.println("构造执行了");
    }

    public static String get() {
        return "111";
    }
}
