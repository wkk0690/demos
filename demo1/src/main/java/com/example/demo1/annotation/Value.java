package com.example.demo1.annotation;

import java.lang.annotation.*;

/**
 * @author wkk
 * @create 2020/08/18
 * @desc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Values.class)
public @interface Value {
    String value() default "value";
}
