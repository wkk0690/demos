package com.example.demo.spider.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author 0X574B4B
 * @create 2018-09-19 10:25
 * @descriptions <p></p >
 */
public class Demo3 {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse execute = httpClient.execute(new HttpGet("http://api.xicidaili.com/free2016.txt"));
        String s = EntityUtils.toString(execute.getEntity());
        System.out.println(s);
    }
}
