package com.example.demo.ThreadCommunication;

/**
 * @author 0X574B4B
 * @create 2018-08-29 18:45
 * @desc 死锁
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        //extracted();
        Teacher teacher = new Teacher();
        Student student = new Student();

        new Thread(() -> {
            teacher.demo1(student);
        }).start();
        new Thread(() -> {
            student.demo1(teacher);
        }).start();
    }
    static class Teacher{
        synchronized void demo1(Student student){
            try {
                System.out.println("enter");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("next");
            student.demo2(this);
        }
        synchronized void demo2(Student student){

        }
    }
    static class Student{
        synchronized void demo1(Teacher teacher){
            try {
                System.out.println("enter");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("next");
            teacher.demo2(this);
        }
        synchronized void demo2(Teacher teacher){

        }
    }





    private static void extracted() {
        Object resource1 = new Object();//资源 1
        Object resource2 = new Object();//资源 2

        new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread() + "get resource1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource2");
                synchronized (resource2) {
                    System.out.println(Thread.currentThread() + "get resource2");
                }
            }
        }, "线程 1").start();

        new Thread(() -> {
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        }, "线程 2").start();
    }
}


