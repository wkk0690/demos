package com.example.demo.percent3;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _8_test {

    static Lock lock = new ReentrantLock();

    @Test
    public void demo1() {
        long t1 = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new MyThread(1, 2000, countDownLatch).start();
        new MyThread(2, 3000, countDownLatch).start();
        new MyThread(3, 2500, countDownLatch).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("总时间： " + (t2 - t1));
    }

    class MyThread extends Thread {

        private int num;
        private int time;
        private CountDownLatch countDownLatch;

        public MyThread(int num, int time, CountDownLatch countDownLatch) {
            this.num = num;
            this.time = time;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println("线程" + num + "开始。。。");
                Thread.sleep(time);
                countDownLatch.countDown();
                System.out.println("线程" + num + "结束。。。");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}



