package com.example.demo.design_mode.creational;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author OX574B4B
 * @create 2021/01/14
 * @desc 创建模式
 * 1: 抽象工厂模式 (Abstract Factory)
 * ThreadFactory  多态 契约 Executors
 * 2: 构建器模式 (Builder)
 * 3: 工厂方法模式 (Factory Method)
 * 4: 原型模式 (Prototype)
 * 5: 单例模式 (Singleton)
 */
public class CreationalPatternsDemo {

    public static void main(String[] args) {
        //抽象工厂
        //工厂方法
        //创造器模式
        //原型
        //单例

        //Builder 模式
        //抽象工厂 CharSequence.toString()
        StringBuilder stringBuilder = new StringBuilder();
        CharSequence cs = stringBuilder;
        //方法是抽象的, java.lang.CharSequence接口
        String value = cs.toString(); //创建抽象工厂 -> 对象是原型

        //Builder -> Fluent API -> Stream
        List<String> values = Arrays.asList("1", "2", "3");
        values.stream().map(String::toString).map(String::hashCode)
                .collect(Collectors.toList());
        // .reduce(Integer::sum);

        // 工厂模式状态性: 有状态. 无状态
        // 有状态(可变, 不可变)
        // 可变: CharSequence
        // 不可变: String
    }

    public static void echo(CharSequence cs) {

    }

    //工厂方法的命名前缀
    //build
    //new
    //to
    //create

    public static String toString(Object value) { //工厂方法
        return value.toString();
    }
}
