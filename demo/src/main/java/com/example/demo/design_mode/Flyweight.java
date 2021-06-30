package com.example.demo.design_mode;

import java.util.HashMap;

/**
 * @author OX574B4B
 * @create 2021/06/25
 * @desc 享元模式
 */
public class Flyweight {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight1 f01 = factory.getFlyweight("a");
        Flyweight1 f02 = factory.getFlyweight("a");
        Flyweight1 f03 = factory.getFlyweight("a");
        Flyweight1 f11 = factory.getFlyweight("b");
        Flyweight1 f12 = factory.getFlyweight("b");
        f01.operation(new UnsharedConcreteFlyweight("第1次调用a。"));
        f02.operation(new UnsharedConcreteFlyweight("第2次调用a。"));
        f03.operation(new UnsharedConcreteFlyweight("第3次调用a。"));
        f11.operation(new UnsharedConcreteFlyweight("第1次调用b。"));
        f12.operation(new UnsharedConcreteFlyweight("第2次调用b。"));
    }
}
//非享元角色
class UnsharedConcreteFlyweight {
    private String info;
    UnsharedConcreteFlyweight(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
}
//抽象享元角色
interface Flyweight1 {
    public void operation(UnsharedConcreteFlyweight state);
}
//具体享元角色
class ConcreteFlyweight implements Flyweight1 {
    private String key;
    ConcreteFlyweight(String key) {
        this.key = key;
        System.out.println("具体享元" + key + "被创建！");
    }
    public void operation(UnsharedConcreteFlyweight outState) {
        System.out.print("具体享元" + key + "被调用，");
        System.out.println("非享元信息是:" + outState.getInfo());
    }
}
//享元工厂角色
class FlyweightFactory {
    private HashMap<String, Flyweight1> flyweights = new HashMap<String, Flyweight1>();
    public Flyweight1 getFlyweight(String key) {
        Flyweight1 flyweight = (Flyweight1) flyweights.get(key);
        if (flyweight != null) {
            System.out.println("具体享元" + key + "已经存在，被成功获取！");
        } else {
            flyweight = new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}