package com.example.demo.ThreadCommunication;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 0X574B4B
 * @create 2018-08-29 19:07
 * @desc 利用线程池的 awaitTermination 方法，每隔一秒钟检查线程池是否执行完毕
 */
public class AwaitTerminationDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(10); //队列

        ThreadPoolExecutor executors = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MILLISECONDS, queue);
        executors.execute(new Thread(new Runner3("jack", 1000)));
        executors.execute(new Thread(new Runner3("rose", 5000)));

        executors.shutdown();
        //executors.awaitTermination(1, TimeUnit.SECONDS);
        while (!executors.awaitTermination(1, TimeUnit.SECONDS)) {
            System.out.println("还有线程没执行完");
        }

        System.out.println("main over");

    }
}

class Runner3 implements Runnable {

    private int time;

    private String name;

    public Runner3(String name, int time) {
        this.time = time;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " 开始...");
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
