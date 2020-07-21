package com.example.demo.mq;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {

    public static void main(String[] args) {
        List list=new ArrayList();
        list.add("daba");
        List list1=new ArrayList();
        list1.add("daa");
        assertEquals(list, list1);
    }
}
