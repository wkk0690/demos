package com.example.demo.spider.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpClien_proxy {

    /**
     *
     * @throws IOException
     */
    @Test
    public void demo3() throws IOException {
        // 创建HttpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建Httpget实例 ，http://2018.ip138.com/ic.asp为该网址返回对应的ip
        //以下为要访问的网址
        HttpGet httpGet = new HttpGet("http://budwuv.v.vote8.cn");

        //代理IP设置，代理 ip查询地址：https://www.xicidaili.com/
        HttpHost httoHost = new HttpHost("122.51.32.173",8888);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//设置连接超时时间,单位毫秒
                .setSocketTimeout(10000)//设置读取超时时间,单位毫秒
                .setProxy(httoHost)//设置代理
                .build();

        httpGet.setConfig(requestConfig);
        //设置Http报文头信息
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");

        CloseableHttpResponse response = httpClient.execute(httpGet); // 执行http get请求

        if (response != null){
            HttpEntity entity = response.getEntity();  //获取返回实体
            if (entity != null){
                System.out.println("网页内容为：");
                System.out.println(EntityUtils.toString(entity,"gbk"));
            }
        }
        if (response != null){
            response.close();
        }
        if (httpClient != null){
            httpClient.close();
        }
    }

    /**
     * https://my.oschina.net/u/188924/blog/3000449 centos7做成代理服务器
     * @throws IOException
     */
    @Test
    public void demo1() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpPost httpPost = new HttpPost("http://122.51.32.173:8082/user/test");
        HttpPost httpPost = new HttpPost("http://47.104.158.115:8099/test");

        //代理
        HttpHost proxy = new HttpHost("122.51.32.173", 8888, "http");
        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
        httpPost.setConfig(config);

        CloseableHttpResponse execute = httpClient.execute(httpPost);
        String result = EntityUtils.toString(execute.getEntity());
        System.out.println(result);
    }
}
