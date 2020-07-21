package com.example.demo.spider.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * webmagic爬虫,爬取天气 xpath解析
 *
 *  文档: http://webmagic.io/docs/zh/posts/ch1-overview/architecture.html
 */
public class WeatherSpider implements PageProcessor {

    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        OOSpider.create(new WeatherSpider())
                .addUrl("http://www.weather.com.cn/weather/101010700.shtml")
                .thread(1)
                .run();
        long t2 = System.currentTimeMillis();
        System.out.println("总时间: " + (t2 - t1));
    }

    public Site getSite() {
        return Site.me().setTimeOut(20000).setRetryTimes(3).setSleepTime(2000).setCharset("UTF-8");
    }

    public void process(Page page) {
        Selectable sevenStr = page.getHtml().xpath("//div[@id='7d']/ul[@class='t clearfix']");//最近7天天气
        Selectable hourStr = page.getHtml().xpath("//div[@id='7d']/script");//分时段天气
        List<String> list = handleSevenDays(sevenStr);
        System.out.println(list);
    }

    private List<String> handleSevenDays(Selectable sevenStr) {
        List<String> sevenDays = sevenStr.xpath("//ul[@class='t clearfix']/li").all();
        List<String> result = new ArrayList<String>();

        if (sevenDays != null && sevenDays.size() > 0) {
            for (String day : sevenDays) {
                Html temp = Html.create(day);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(temp.xpath("//h1/text()").toString());
                stringBuffer.append("," + temp.xpath("//p[@class='wea']/text()").toString());
                stringBuffer.append("," + temp.xpath("//p[@class='tem']/allText()").toString());

                List<String> windList = temp.xpath("//p[@class='win']/em/span").all();
                String windStr = ",";
                if (windList != null && windList.size() > 0) {
                    for (String win : windList) {
                        Html winHtml = Html.create(win);
                        windStr = windStr + winHtml.xpath("//span/@title") + "/";
                    }
                }
                stringBuffer.append(windStr.substring(0, windStr.length() - 1));
                stringBuffer.append("," + temp.xpath("//p[@class='win']/i/text()").toString());

                result.add(stringBuffer.toString());
            }
        }

        return result;
    }
}
