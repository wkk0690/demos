package com.example.demo.percent3;

import org.junit.jupiter.api.Test;

/**
 * synchronized锁对象 是当前类对象, 一个类中两个方法 会, 互相卡锁对象
 */
public class _10_SynchronizedTest2 {
    long t1 = 0l;
    long t2 = 0l;
    long t3 = 0l;
    long t4 = 0l;
    long t5 = 0l;

    private Object obj1 = new Object();
    private Object obj2 = new Object();

    @Test
    public void demo1() {
        t1 = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                aa();
                t2 = System.currentTimeMillis();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                bb();
                t3 = System.currentTimeMillis();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                cc();
                t4 = System.currentTimeMillis();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                dd();
                t5 = System.currentTimeMillis();
            }
        }.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(t2 - t1);
        System.out.println(t3 - t1);
        System.out.println(t4 - t1);
        System.out.println(t5 - t1);
    }


    public synchronized void aa() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bb() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void cc() {
        synchronized (obj1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void dd() {
        synchronized (obj2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
