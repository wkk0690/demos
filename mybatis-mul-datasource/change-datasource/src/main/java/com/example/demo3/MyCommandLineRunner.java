package com.example.demo3;

import com.example.demo3.utils.SpringBootBeanUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author 0X574B4B
 * @create 2020/07/22
 * @desc
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("22222222222"+SpringBootBeanUtils.getApplicationContext());
    }
}
