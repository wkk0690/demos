package com.example.demo.ThreadCommunication;

import java.util.concurrent.CountDownLatch;

/**
 * @author 0X574B4B
 * @create 2018-08-29 19:32
 * @desc CountDownLatch用来协调多个线程之间的同步
 */
public class CountDownLatchDemo {

    /**
     * 不足:计数器的值只能在构造方法中初始化一次;
     */
    public static void main(String[] args) throws InterruptedException {
        int num = 3;
        long start = System.currentTimeMillis();
        final CountDownLatch countDown = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                System.out.println("start");
                try {
                    Thread.sleep(2000);
                    countDown.countDown(); //每当一个任务线程执行完毕，就将计数器减1
                    System.out.println("end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDown.await(); //当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒

        long stop = System.currentTimeMillis();
        System.out.println("main over total time=" + (stop - start));
    }
}
