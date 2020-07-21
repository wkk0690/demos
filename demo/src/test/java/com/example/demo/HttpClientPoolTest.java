package com.example.demo;

import com.example.demo.httppool.HttpClientPool;
import com.example.demo.httppool.gongjulei;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Test;

/**
 * @author 0X574B4B
 * @create 2018-08-22 9:24
 * @descriptions <p></p >
 */
public class HttpClientPoolTest {


    /**
     * httpclient连接池测试
     */
    @Test
    public void demo1() {
        CloseableHttpClient httpClient = HttpClientPool.getHttpClient();
        System.out.println(httpClient);

        CloseableHttpClient httpClient1 = HttpClientPool.getHttpClient();
        System.out.println(httpClient1);
    }

}