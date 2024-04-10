package com.example.demo.boot;

import org.springframework.util.StopWatch;

public class Example {

    public static void main(String[] args) {
        measureExecutionTime();
    }

    public static void measureExecutionTime() {
        StopWatch stopWatch = new StopWatch("My Task");

        stopWatch.start("Task 1");
        // 执行任务1的代码
        stopWatch.stop();

        stopWatch.start("Task 2");
        // 执行任务2的代码
        stopWatch.stop();

        // 输出所有任务的总耗时和每个任务的详细耗时
        System.out.println(stopWatch.prettyPrint());
    }
}