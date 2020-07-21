package com.example.demo.spider.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

public class MyClass {

    public static void main(String[] args) {

        //全局代理
//        System.setProperty("http.proxyHost", "61.135.217.7");
//        System.setProperty("http.proxyPort", "80");
//        System.setProperty("https.proxyHost", "61.135.217.7");
//        System.setProperty("https.proxyPort", "80");
//        try {
//            URL url = new URL("http://localhost:8081/vct/api/coin/currency_lists");
//            URLConnection connection = url.openConnection();
//            System.out.println(ParseStream(connection.getInputStream()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //设置Proxy对象
        SocketAddress address = new InetSocketAddress("192.168.1.13", 8081);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
        try {
            URL url = new URL("http://localhost:8081/vct/api/coin/currency_lists");
            URLConnection connection = url.openConnection(proxy);
            System.out.println(ParseStream(connection.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String ParseStream(InputStream stream) {
        StringBuilder builder = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String strtmp;
            try {
                strtmp = reader.readLine();
                while (null != strtmp) {
                    builder.append(strtmp);
                    builder.append("\n");
                    strtmp = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}

