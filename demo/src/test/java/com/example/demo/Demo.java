package com.example.demo;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 0X574B4B
 * @create 2018-09-14 14:15
 * @descriptions <p></p >
 */
public class Demo {

    @Test
    public void demo1() throws UnknownHostException {
        InetAddress[] allByName = InetAddress.getAllByName("192.168.1.10");
        System.out.println(allByName[0].getAddress());
        System.out.println(allByName[0].getHostAddress());
        System.out.println(allByName[0].getCanonicalHostName());
        System.out.println(allByName[0].getHostName());
    }

    @Test
    public void demo2() throws UnknownHostException {
        InetAddress[] wangkaikais = InetAddress.getAllByName("wangkaikai");
        System.out.println(wangkaikais[0].getHostName());
        System.out.println(wangkaikais[0].getHostAddress());
    }
}
