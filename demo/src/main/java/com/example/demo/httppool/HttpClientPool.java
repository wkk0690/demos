package com.example.demo.httppool;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author 0X574B4B
 * @create 2018-08-22 9:20
 * @descriptions <p>httpclient连接池</p >
 */
public class HttpClientPool {
    private static PoolingHttpClientConnectionManager cm = null;

    static {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(400); //最大连接数
        cm.setDefaultMaxPerRoute(50); //每个路由最大连接数
    }

    public static CloseableHttpClient getHttpClient() {
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(globalConfig).build();
        return client;
    }

}