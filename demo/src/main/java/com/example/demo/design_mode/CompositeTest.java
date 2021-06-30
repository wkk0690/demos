package com.example.demo.design_mode;

import java.util.ArrayList;

/**
 * @author OX574B4B
 * @create 2021/06/25
 * @desc
 */
public class CompositeTest {
    public static void main(String[] args) {
        Component1 c0 = new Composite();
        Component1 c1 = new Composite();
        Component1 leaf1 = new Leaf("1");
        Component1 leaf2 = new Leaf("2");
        Component1 leaf3 = new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }
}
//抽象构件
interface Component1 {
    public void add(Component1 c);
    public void remove(Component1 c);
    public Component1 getChild(int i);
    public void operation();
}
//树叶构件
class Leaf implements Component1 {
    private String name;
    public Leaf(String name) {
        this.name = name;
    }
    public void add(Component1 c) {
    }
    public void remove(Component1 c) {
    }
    public Component1 getChild(int i) {
        return null;
    }
    public void operation() {
        System.out.println("树叶" + name + "：被访问！");
    }
}
//树枝构件
class Composite implements Component1 {
    private ArrayList<Component1> children = new ArrayList<Component1>();
    public void add(Component1 c) {
        children.add(c);
    }
    public void remove(Component1 c) {
        children.remove(c);
    }
    public Component1 getChild(int i) {
        return children.get(i);
    }
    public void operation() {
        for (Object obj : children) {
            ((Component1) obj).operation();
        }
    }
}
