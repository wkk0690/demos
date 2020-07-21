package com.example.demo.percent3;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class _10_CountDownLatch {

    @Test
    public void demo1() {
        long t1 = System.currentTimeMillis();
        ExecutorService exe = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i <= 10; i++) {
            exe.execute(new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("总时间" + (t2 - t1));
    }
}
