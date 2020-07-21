package com.example.demo.ThreadCommunication;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1306580911915042
 * wait() 和 noticy()
 */
public class WaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue queue = new TaskQueue();
        new Thread(){
            @Override
            public void run() {
                queue.getTask();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                queue.addTask("123");
            }
        }.start();

    }
}

class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        this.notify(); // 唤醒在this锁等待的线程(随机一个)
        this.notifyAll(); // 唤醒在this锁等待的线程(所有)
    }

    public synchronized String getTask() {
        while (queue.isEmpty()) {
            // 释放this锁:
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 重新获取this锁
        }
        return queue.remove();
    }
}

