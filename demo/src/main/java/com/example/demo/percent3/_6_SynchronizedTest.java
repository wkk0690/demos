package com.example.demo.percent3;

public class _6_SynchronizedTest {

    static Object obj = new Object(); //锁对象

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        System.out.println("线程1 wait。。。");
                        obj.wait();
                        System.out.println("线程1 结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    try {
                        Thread.sleep(1000);
                        System.out.println("线程2 notify。。。");
                        obj.notify();
                        // obj.notifyAll(); //和notify类似,只不过是唤醒该notifyAll方法所属对象的等待集中的所有线程
                        System.out.println("线程2 结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}