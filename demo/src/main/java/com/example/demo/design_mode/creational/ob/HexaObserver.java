package com.example.demo.design_mode.creational.ob;

/**
 * @author OX574B4B
 * @create 2020/11/23
 * @desc
 */
public class HexaObserver extends Observer{

    public HexaObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: " + subject.getState());
    }
}
