package com.example.demo.ThreadCommunication;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 0X574B4B
 * @create 2018-08-29 18:45
 * @desc 线程通信方式一: CyclicBarrier
 * 和CountDownLatch区别，CyclicBarrier可以重复使用 barrier.reset();
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executors = Executors.newFixedThreadPool(3);
        executors.submit(new Thread(new Runner(barrier, "jack", 1000)));
        executors.submit(new Thread(new Runner(barrier, "rose", 10000)));
        executors.submit(new Thread(new Runner(barrier, "tom", 5000)));

        executors.shutdown();
    }
}

class Runner implements Runnable {

    private CyclicBarrier barrier;

    private String name;

    private int time;

    public Runner(CyclicBarrier barrier, String name, int time) {
        super();
        this.barrier = barrier;
        this.time = time;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(time));
            System.out.println(name + ": Iam ready...");
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(name + " run ...");
    }
}
