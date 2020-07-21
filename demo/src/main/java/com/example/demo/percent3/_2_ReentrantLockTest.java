package com.example.demo.percent3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁的 对比
 */
public class _2_ReentrantLockTest {

    //static Lock lock = new ReentrantLock(true); //公平锁
    static Lock lock = new ReentrantLock(false); //非公平锁

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }
    }

    static class ThreadDemo implements Runnable {
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override

        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 2; i++) {
                lock.lock();
                System.out.println("获得锁的线程：" + id);
                lock.unlock();
            }
        }
    }
}
