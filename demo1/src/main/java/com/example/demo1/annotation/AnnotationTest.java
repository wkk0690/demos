package com.example.demo1.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author 0X574B4B
 * @create 2020/08/18
 * @desc
 */
public class AnnotationTest {

    @Value("123")
    @Value("456")
    class Student{

    }

    public static void main(String[] args) {
        Values values = Student.class.getAnnotation(Values.class);
        for (Value value : values.value()) {
            System.out.println(value.value());
        }
    }
}


