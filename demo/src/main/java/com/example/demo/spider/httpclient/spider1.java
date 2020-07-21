package com.example.demo.spider.httpclient;

import com.example.demo.httppool.HttpClientPool;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @create 2018-06-14 9:29
 * @desc httpclient的爬虫 httpclient爬取, jsoup解析html
 */
public class spider1 {

    public static void main(String[] args) throws IOException {
        List<Map> bourses = bourse();
        System.out.println(bourses);
    }

    /**
     * 交易所及详情
     *
     * @return
     * @throws IOException
     */
    public static List<Map> bourse() throws IOException {
        List<Map> list = new ArrayList<Map>();
        //抓取交易所信息
        int pages = 6;
        for (int i = 0; i < pages; i++) {
            getBourses("https://www.feixiaohao.com/exchange/list_" + (i + 1) + ".html", list);
        }
        return list;
    }

    /**
     * 获取document
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static Document spider(String url) throws IOException {
        //HttpClient client = new DefaultHttpClient(); //new一个
        HttpClient client = HttpClientPool.getHttpClient(); //连接池
        HttpResponse response = client.execute(new HttpGet(url));
        //System.out.println(response.getStatusLine()); http1.1 200
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
        //System.out.println(IOUtils.toString(br));
        return Jsoup.parse(IOUtils.toString(br));
    }

    static void getBourses(String url, List<Map> list) throws IOException {
        Document document = spider(url);
        Elements tbody = document.getElementsByTag("tbody");
        for (Element element : tbody.get(0).children()) { //tr
            Map dataMap = new HashMap();
            Elements children = element.children(); //td
            String name = children.get(1).child(0).text();//名字
            String ename = children.get(1).child(0).attr("href").split("/")[2];//ename
            String logo = children.get(1).child(0).child(0).attr("src");
            String amount = children.get(2).text(); //24小时成交额
            String symbols = children.get(3).text(); //交易对
            String country = children.get(4).text(); //国家
            String star = children.get(6).child(0).attr("class").split(" ")[1]; //星级
            dataMap.put("name", name);
            dataMap.put("ename", ename);
            dataMap.put("logo", logo);
            dataMap.put("amount", amount);
            dataMap.put("symbols", symbols);
            dataMap.put("country", country);
            dataMap.put("star", star);

            getCoins("https://www.feixiaohao.com/exchange/" + ename + "/", dataMap);

            list.add(dataMap);
        }
    }


    static void getCoins(String url, Map map) throws IOException {
        Document document = spider(url);
        Elements section = document.getElementsByTag("section");
        String introduction = section.get(0).text(); //简介
        String feeExplain = "";//费用说明
        if (section.size() > 1) feeExplain = section.get(1).text();//费用说明

        Elements marketinfo = document.getElementsByAttributeValue("class", "marketinfo");
        Elements tagRank = document.getElementsByAttributeValue("class", "tag-rank");
        String desc = marketinfo.get(0).child(1).child(3).text();//描述
        Element child = marketinfo.get(0).child(1).child(5).child(0);//官网
        String rank = "";
        String weibo = "";
        String site = "";
        if (child.children().size() > 0) {
            site = child.child(0).text();
        }
        if (tagRank.size() > 0 && tagRank.get(0).children().size() > 0) {
            rank = document.getElementsByAttributeValue("class", "tag-rank").get(0).child(0).text();
        }
        if (marketinfo.get(0).children().size() > 1 && marketinfo.get(0).child(1).children().size() > 5 && marketinfo.get(0).child(1).child(5).children().size() > 2
                && marketinfo.get(0).child(1).child(5).child(2).children().size() > 0)
            weibo = "https:" + marketinfo.get(0).child(1).child(5).child(2).child(0).attr("href");
        map.put("feeExplain", feeExplain);
        map.put("introduction", introduction);
        map.put("desc", desc);
        map.put("site", site);
        map.put("weibo", weibo);
        map.put("rank", rank);

        Elements tbody = document.getElementsByTag("tbody");
        List<Map> list = new ArrayList<>();
        for (Element element : tbody.get(0).children()) { //tr
            Map dataMap = new HashMap();
            String logo = element.child(1).child(0).child(0).attr("src");
            String name = element.child(1).child(0).text();
            String symbol = element.child(2).text();
            String price = element.child(3).text();
            String vols = element.child(4).text(); //成交量
            String amount = element.child(5).text(); //成交额
            String percent = element.child(6).text(); //占比

            dataMap.put("logo", logo);
            dataMap.put("name", name);
            dataMap.put("symbol", symbol);
            dataMap.put("price", price);
            dataMap.put("vols", vols);
            dataMap.put("amount", amount);
            dataMap.put("percent", percent);
            list.add(dataMap);
        }
        map.put("list", list);
    }
}
