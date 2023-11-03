package com.example.demo.algorithm;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 */
public class MyDelay implements Delayed {
    long delayTime; // 延迟时间
    long expire; // 过期时间
    String data;

    public MyDelay(long delayTime, String t) {
        this.delayTime = delayTime;
        // 过期时间 = 当前时间 + 延迟时间
        this.expire = System.currentTimeMillis() + delayTime;
        data = t;
    }

    /**
     * 剩余时间 = 到期时间 - 当前时间
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 优先级规则：两个任务比较，时间短的优先执行
     */
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Delayed> queue = new DelayQueue();
        queue.add(new MyDelay(5000, "第一次添加任务"));
        queue.add(new MyDelay(3000, "第二次添加任务"));
        queue.add(new MyDelay(6000, "第三次添加任务"));

        while (!queue.isEmpty()) {
            MyDelay delayed = (MyDelay) queue.take();
            System.out.println(delayed.data);
        }
    }
}
