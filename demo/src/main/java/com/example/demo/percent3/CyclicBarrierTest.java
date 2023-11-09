package com.example.demo.percent3;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 内部维护了一个计数器和一个栅栏状态，每次调用 await() 方法都会将计数器减 1，当计数器的值为 0 时，所有线程都已到达同步点，会被唤醒继续执行后续操作，并且栅栏状态会被重置。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 准备好了");
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 开始干活了");
                barrier.reset();
                System.out.println(Thread.currentThread().getName() + " 第二次准备好了");
                try {
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 第二次开始干活了");
            }, "t" + i).start();
        }

    }
}
