package com.example.demo.task.quartz;

import org.junit.jupiter.api.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * https://www.w3cschool.cn/quartz_doc/
 */
public class QuartzTest {

    @Test
    public void demo1(){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            //任务
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    //在job的执行过程中，获取这些参数
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("myFloatValue", 3.141f)
                    .build();

            //触发器
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1) //1s1次
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);

            Thread.sleep(4000);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
