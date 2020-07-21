package com.example.demo.ThreadCommunication;

/**
 * @author 0X574B4B
 * @create 2018-08-29 18:58
 * @desc 采用 join 线程间通信
 */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runner1("JACK", 7000));
        Thread t2 = new Thread(new Runner1("rose", 5000));

        t1.start();
        t2.start();

        /**join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
         程序在main线程中调用t1线程的join方法，则main线程放弃cpu控制权，并返回t1线程继续执行直到线程t1执行完毕
         所以结果是t1线程执行完后，才到主线程执行，相当于在main线程中同步t1线程，t1执行完了，main线程才有执行的机会
         */
        t1.join(); //等待线程1终止
        t2.join(); //等待线程2终止

        System.out.println("main voer");
    }
}

class Runner1 implements Runnable {

    private int time;

    private String name;

    public Runner1(String name, int time) {
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