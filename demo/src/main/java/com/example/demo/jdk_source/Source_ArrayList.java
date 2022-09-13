package com.example.demo.jdk_source;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Handler;

/**
 * @author 0X574B4B
 * @create 2020/05/13
 * @desc arraylist的源码分析
 *  https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/collection/ArrayList-Grow.md
 */
public class Source_ArrayList {

    /**
     * 测试ensureCapacity 方法
     * 2823
     */
    @Test
    public void demo1(){
        final int N = 10000000;
        Object obj = new Object();

        //没用调用ensureCapacity()方法初始化ArrayList对象
        ArrayList list = new ArrayList();
        long startTime = System.currentTimeMillis();
        for(int i=0;i<=N;i++){
            list.add(obj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("没有调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");

        //调用ensureCapacity()方法初始化ArrayList对象
        list = new ArrayList();
        startTime = System.currentTimeMillis();
        list.ensureCapacity(N);//预先设置list的大小
        for(int i=0;i<=N;i++){
            list.add(obj);
        }
        endTime = System.currentTimeMillis();
        System.out.println("调用ensureCapacity()方法所用时间：" + (endTime - startTime) + "ms");
    }

    public void demo2(){
        HashMap hashMap = new HashMap(2);
        System.out.println(this.hashCode());

    }
}
