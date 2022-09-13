package com.example.demo3;

import com.example.demo3.utils.SpringBootBeanUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author 0X574B4B
 * @create 2020/07/22
 * @desc
 */
public class MyListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("66666666666" + SpringBootBeanUtils.getApplicationContext());
    }
}
