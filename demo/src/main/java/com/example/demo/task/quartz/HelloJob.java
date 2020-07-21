package com.example.demo.task.quartz;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * 两个注解尽量一起使用, 因为当同一个job（JobDetail）的两个实例被并发执行时，
 * 由于竞争，JobDataMap中存储的数据很可能是不确定的。
 */

//不加可以并发, 同一时刻仅允许执行一个实例
// @DisallowConcurrentExecution

//Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，
// 使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据。
@PersistJobDataAfterExecution //
public class HelloJob implements Job {

    //获取参数方法2, 提供setter方法
    String jobSays;
    float myFloatValue;
    ArrayList state;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("执行了.............");

        JobKey key = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        //获取参数 方法1
//        String jobSays = dataMap.getString("jobSays");
//        float myFloatValue = dataMap.getFloat("myFloatValue");
//        ArrayList state = (ArrayList)dataMap.get("myStateData");
//        state.add(new Date());
        System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
    }

    public void setJobSays(String jobSays) {
        this.jobSays = jobSays;
    }

    public void setMyFloatValue(float myFloatValue) {
        this.myFloatValue = myFloatValue;
    }

    public void setState(ArrayList state) {
        this.state = state;
    }
}
