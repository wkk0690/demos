package com.example.demo.spider.httpclient;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 0X574B4B
 * @create 2018-09-19 9:56
 * @descriptions <p></p >
 */
public class Demo2_java {

    public static void main(String[] args) throws Exception {
        System.getProperties().setProperty("http.proxyHost", "61.135.217.7");
        System.getProperties().setProperty("http.proxyPort", "80");

        System.out.println(getHtml("http://city.ip138.com/ip2city.asp"));
    }

    private static String getHtml(String address) throws Exception {
        StringBuffer html = new StringBuffer();
        String result = "";
        String inputLine = null;
        byte[] bu = new byte[4096];
        int byteRead = 0;
        BufferedInputStream inputStream = null;

        URL url = new URL(address);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2227.0 Safari/537.36");
        connection.getInputStream();
        inputStream = new BufferedInputStream(connection.getInputStream());

        while (byteRead >= 0) {
            inputLine = new String(bu, 0, byteRead, "ISO-8859-1");
            html.append(inputLine);
            byteRead = inputStream.read(bu);
            inputLine = null;
        }

        bu = null;

        result = new String(html.toString().trim().getBytes("ISO-8859-1"), "UTF-8".toString());
        return result;
    }
}
