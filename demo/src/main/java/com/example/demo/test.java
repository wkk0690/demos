package com.example.demo;

import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void demo1(){
        Boolean flag = true;
        aa(flag);
        System.out.println(flag);
    }

    private void aa(Boolean flag) {
        flag = false;
    }

    @Test
    public void demo2(){
        Integer a = Integer.parseInt("1");
        bb(a);
        System.out.println(a);
    }

    private void bb(Integer a) {
        a = Integer.valueOf(2);
    }
}
