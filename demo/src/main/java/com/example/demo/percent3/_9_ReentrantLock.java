package com.example.demo.percent3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _9_ReentrantLock {
    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(10);
        long time1 = System.currentTimeMillis();
        for (int i = 1; i <= 10; i++) {
            exe.execute(new MyThread());
        }
        exe.shutdown();//关闭线程池,不会真的关闭,只是不接收新任务
        while (true) {
            //判断是否所有线程都执行完毕
            if (exe.isTerminated()) {
                System.out.println("结束了！");
                long time2 = System.currentTimeMillis();
                System.out.println("花费时间:" + (time2 - time1));
                break;
            }
            Thread.sleep(20);//防止循环很多次
        }
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        _9_ReentrantLock.lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始...");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "结束.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            _9_ReentrantLock.lock.unlock();
        }
    }
}
