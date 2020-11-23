package com.example.demo.design_mode.ob;

/**
 * @author OX574B4B
 * @create 2020/11/23
 * @desc
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: " + subject.getState());
    }
}
