package com.example.demo.spider.httpclient;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author 0X574B4B
 * @create 2018-09-19 9:54
 * @desc 全局设置端口好和ip地址
 * <p>
 * （System.getProperties().setProperty() == System.setProperty()） --http://city.ip138.com/ip2city.asp 测试IP
 * System.setProperty 相当于一个静态变量 ，存在内存里面！
 * 可以在项目的任何一个地方 通过System.getProperty("变量");来获得，
 */
public class Demo1_Jsonp {

    public static void main(String[] args) throws IOException {
        System.getProperties().setProperty("proxySet", "true");
        System.getProperties().setProperty("http.proxyHost", "122.51.32.173");
        System.getProperties().setProperty("http.proxyPort", "8888");

        Connection connect = Jsoup.connect("http://ekp.22tianbo.com:100/login.jsp");
        connect.timeout(2000);


        Document document = connect.get();
        System.out.println(document);
    }
}
