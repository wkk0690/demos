package com.example.demo.task;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author 0X574B4B
 * @create 2018-11-23 13:38
 * @desc ScheduledExecutor
 */
public class Demo_ScheduledExecutor {

    /**
     * 鉴于 Timer 的上述缺陷，Java 5 推出了基于线程池设计的 ScheduledExecutor。
     * 其设计思想是，每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。需要注意的是，
     * 只有当任务的执行时间到来时，ScheduedExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。
     */

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);

    /**
     * 简单api
     */
    @Test
    public void demo1() {
        //scheduledFuture.cancel(true);取消

        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("第一个");
            }
        }, 1, 1, TimeUnit.SECONDS);

        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("第二个");
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    /**
     * Timer 和 ScheduledExecutor 都仅能提供基于开始时间与重复间隔的任务调度，不能胜任更加复杂的调度需求。
     * 比如，设置每星期二的 16:38:10 执行任务。(计算最近的一次需要多少s)
     */
    @Test
    public void demo2() {
        //获取当前时间
        Calendar currentDate = Calendar.getInstance();
        long currentDateLong = currentDate.getTime().getTime();
        System.out.println("Current Date = " + currentDate.getTime().toString());
        //计算满足条件的最近一次执行时间
        Calendar earliestDate = getEarliestDate(currentDate, 3, 16, 38, 10);
        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("Earliest Date = "
                + earliestDate.getTime().toString());
        //计算从当前时间到最近一次执行时间的时间间隔
        long delay = earliestDateLong - currentDateLong;
        //计算执行周期为一星期
        long period = 7 * 24 * 60 * 60 * 1000;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        //从现在开始delay毫秒之后，每隔一星期执行一次job1
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        }, delay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 计算从当前时间currentDate开始，满足条件dayOfWeek, hourOfDay,minuteOfHour, secondOfMinite的最近时间
     *
     * @return
     */
    public Calendar getEarliestDate(Calendar currentDate, int dayOfWeek,
                                    int hourOfDay, int minuteOfHour, int secondOfMinite) {
        //计算当前时间的WEEK_OF_YEAR,DAY_OF_WEEK, HOUR_OF_DAY, MINUTE,SECOND等各个字段值
        int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
        int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        int currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);
        int currentSecond = currentDate.get(Calendar.SECOND);

        //如果输入条件中的dayOfWeek小于当前日期的dayOfWeek,则WEEK_OF_YEAR需要推迟一周
        boolean weekLater = false;
        if (dayOfWeek < currentDayOfWeek) {
            weekLater = true;
        } else if (dayOfWeek == currentDayOfWeek) {
            //当输入条件与当前日期的dayOfWeek相等时，如果输入条件中的
            //hourOfDay小于当前日期的
            //currentHour，则WEEK_OF_YEAR需要推迟一周
            if (hourOfDay < currentHour) {
                weekLater = true;
            } else if (hourOfDay == currentHour) {
                //当输入条件与当前日期的dayOfWeek, hourOfDay相等时，
                //如果输入条件中的minuteOfHour小于当前日期的
                //currentMinute，则WEEK_OF_YEAR需要推迟一周
                if (minuteOfHour < currentMinute) {
                    weekLater = true;
                } else if (minuteOfHour == currentSecond) {
                    //当输入条件与当前日期的dayOfWeek, hourOfDay，
                    //minuteOfHour相等时，如果输入条件中的
                    //secondOfMinite小于当前日期的currentSecond，
                    //则WEEK_OF_YEAR需要推迟一周
                    if (secondOfMinite < currentSecond) {
                        weekLater = true;
                    }
                }
            }
        }
        if (weekLater) {
            //设置当前日期中的WEEK_OF_YEAR为当前周推迟一周
            currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);
        }
        // 设置当前日期中的DAY_OF_WEEK,HOUR_OF_DAY,MINUTE,SECOND为输入条件中的值。
        currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minuteOfHour);
        currentDate.set(Calendar.SECOND, secondOfMinite);
        return currentDate;

    }
}

