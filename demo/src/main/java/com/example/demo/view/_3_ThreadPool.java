package com.example.demo.view;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * Java线程池的原理？线程池有哪些？线程池工厂有哪些线程池类型，及其线程池参数是什么？
 * 参数:
 * corePoolSize：
 * 核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。在创建了线程池后，默认情况下，线程池中并没有任何线程，
 * 而是等待有任务到来才创建线程去执行任务，除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，
 * 从这2个方法的名字就可以看出，是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。
 * 默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
 * 当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
 * maximumPoolSize：
 * 线程池最大线程数，这个参数也是一个非常重要的参数，它表示在线程池中最多能创建多少个线程
 * keepAliveTime：
 * 表示线程没有任务执行时最多保持多久时间会终止。默认情况下，只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，
 * 直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，如果一个线程空闲的时间达到keepAliveTime，
 * 则会终止，直到线程池中的线程数不超过corePoolSize。但是如果调用了allowCoreThreadTimeOut(boolean)方法，
 * 在线程池中的线程数不大于corePoolSize时，keepAliveTime参数也会起作用，直到线程池中的线程数为0；
 * unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
 * TimeUnit.DAYS;               //天
 * TimeUnit.HOURS;             //小时
 * TimeUnit.MINUTES;           //分钟
 * TimeUnit.SECONDS;           //秒
 * TimeUnit.MILLISECONDS;      //毫秒
 * TimeUnit.MICROSECONDS;      //微妙
 * TimeUnit.NANOSECONDS;       //纳秒
 * workQueue：一个阻塞队列，用来存储等待执行的任务，这个参数的选择也很重要，会对线程池的运行过程产生重大影响，一般来说，这里的阻塞队列有以下几种选择：ArrayBlockingQueue和PriorityBlockingQueue使用较少，一般使用LinkedBlockingQueue和Synchronous。线程池的排队策略与BlockingQueue有关。
 * threadFactory：线程工厂，主要用来创建线程；
 * handler：表示当拒绝处理任务时的策略，有以下四种取值：
 * 　　ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
 * 　　ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
 * 　　ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
 * 　　ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
 *
 * FixedThreadPool(n)：创建一个数量固定的线程池，超出的任务会在队列中等待空闲的线程，可用于控制程序的最大并发数。
 * CachedThreadPool()：短时间内处理大量工作的线程池，会根据任务数量产生对应的线程，并试图缓存线程以便重复使用，如果限制 60 秒没被使用，则会被移除缓存。
 * SingleThreadExecutor()：创建一个单线程线程池。
 * ScheduledThreadPool(n)：创建一个数量固定的线程池，支持执行定时性或周期性任务。
 * SingleThreadScheduledExecutor()：此线程池就是单线程的 newScheduledThreadPool。
 * WorkStealingPool(n)：Java 8 新增创建线程池的方法，创建时如果不设置任何参数，则以当前机器处理器个数作为线程个数，此线程池会并行处理任务，不能保证执行顺序。
 */
public class _3_ThreadPool {

    /**
     *
     */
    @Test
    public void demo2() {
        //ExecutorService executorService = Executors.newFixedThreadPool(2);

        ExecutorService executorService1 = Executors.newWorkStealingPool();
        for(int i  = 0; i < 100; i++) {
            executorService1.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

}
