package com.example.demo2.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用http发送方法
 */
@Slf4j
public class HttpUtils {

    public static String sendGet(String url) {
        return sendGet(url, null, null);
    }

    /**
     * 发送get请求
     * @param url 路径
     * @param head 头参数
     * @param param 参数
     * @return
     */
    public static String sendGet(String url, JSONObject head, JSONObject param) {
        try {
            // 设置请求的参数(字符串和对象)
            List<String> list = new ArrayList<>();
            if(param != null) {
                for (Object key : param.keySet()) {
                    Object obj = param.get(key);
                    String value = "";
                    if(obj instanceof String) {
                        value = key + "=" + obj;
                    } else {
                        //params[beginTime]=2023-04-03&params[endTime]=2023-05-17
                        JSONObject params = (JSONObject)obj;
                        for (Object subkey : params.keySet()) {
                            list.add(URLEncoder.encode(String.format("%s[%s]", key, subkey), "utf-8") + "=" + params.get(subkey));
                        }
                    }
                    list.add(value);
                }
            }
            if(list.size() > 0) {
                url += "?" + StringUtils.join(list, "&");
            }

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpGet method = new HttpGet(url);

            if(head != null) {
                // 设置请求的header
                for (Object key : head.keySet()) {
                    method.addHeader(key.toString(), head.getString(key.toString()));
                }
            }
            HttpResponse response = httpclient.execute(method);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            log.error("调用HttpsUtil.sendGet Exception, url={}, header={}, param={}", url, head.toString(), param.toString(), e);
        }
        return "";
    }

    public static String sendPost(String url, JSONObject head, JSONObject param) {
        return sendPost(url ,head, param, null);
    }
    public static String sendPost(String url, JSONObject head, String body) {
        return sendPost(url ,head, null, body);
    }

    /**
     * post请求
     * @param url 路径
     * @param head 头参数
     * @param param 键值对参数
     * @param body body参数
     * @return
     */
    public static String sendPost(String url, JSONObject head, JSONObject param, String body) {
        String result = "";
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            //SSLClient.wrapClient(HttpClientBuilder.create().build());
            HttpPost method = new HttpPost(url);
            // 请求头
            if(head != null) {
                for (Object key : head.keySet()) {
                    method.addHeader(key.toString(), head.getString(key.toString()));
                }
            }
            //param参数
            if(param != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                for (Object key : param.keySet()) {
                    nvps.add(new BasicNameValuePair(key.toString(), param.getString(key.toString())));
                }
                method.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            }
            //body参数
            if(body != null) {
                ByteArrayEntity reqEntity = new ByteArrayEntity(body.getBytes("UTF-8"));
                reqEntity.setContentEncoding("UTF-8");
                method.setEntity(reqEntity);
            }

            HttpResponse response = httpclient.execute(method);
            if (response != null) {
                result = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendPost Exception, url={}, header={}, param={}, body={}", url, head.toString(), param.toString(), body.toString(), e);
        }
        return result;
    }

    /**
     * put请求
     * @param url
     * @param head
     * @param param
     * @return
     */
    public static String sendPut(String url, JSONObject head, JSONObject param) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPut method = new HttpPut(url);
            // 设置请求的header
            if(head != null) {
                for (Object key : head.keySet()) {
                    method.addHeader(key.toString(), head.getString(key.toString()));
                }
            }
            // 设置请求的参数
            ByteArrayEntity reqEntity = new ByteArrayEntity(param.toString().getBytes("UTF-8"));
            reqEntity.setContentEncoding("UTF-8");
            method.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(method);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            log.error("调用HttpsUtil.sendPut Exception, url={}, header={}", url, head.toString(), e);
        }
        return "";
    }

    /**
     * delete请求
     * @param url 路径
     * @param head 头参数
     * @return
     */
    public static String sendDelete(String url, JSONObject head, JSONObject param) {
        try {
            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpDelete method = new HttpDelete(url);
            // 设置请求的header
            if(head != null) {
                for (Object key : head.keySet()) {
                    method.addHeader(key.toString(), head.getString(key.toString()));
                }
            }
            // 设置请求的参数
            HttpResponse response = httpclient.execute(method);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendDelete Exception, url={}, header={}", url, head.toString(), e);
        }
        return "";
    }
}