package com.example.demo4;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo4.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author OX574B4B
 * @create 2021/05/27
 * @desc
 */
public class Test {

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();

        for(int j = 0; j < 1491; j ++) {
            int pn = j * 30;

            HttpResponse response = HttpUtils.doGet("https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_id=28204&from_mid=1&&format=json&ie=utf-8&oe=utf-8&query=%E6%88%90%E8%AF%AD%E5%A4%A7%E5%85%A8&sort_key=&sort_type=1&stat0=&stat1=&stat2=&stat3=&pn="+pn+"&rn=30&cb=jQuery110202453027286584808_1622105861470&_=1622105861535");
            String s = EntityUtils.toString(response.getEntity());
            s = s.substring(s.indexOf("(") + 1, s.length() - 1);
            JSONObject jsonObject = JSONObject.parseObject(s);
            JSONArray data = jsonObject.getJSONArray("data");
            if(data.size() == 0) continue;
            JSONObject jsonObject1 = data.getJSONObject(0);
            if(jsonObject1 == null) continue;
            JSONArray result = jsonObject1.getJSONArray("result");
            for (int i = 0; i < result.size(); i ++) {
                JSONObject job = result.getJSONObject(i);
                list.add(job.getString("ename"));
            }
        }



        String s1 = Arrays.toString(list.toArray());
        System.out.println(s1);
    }
}
