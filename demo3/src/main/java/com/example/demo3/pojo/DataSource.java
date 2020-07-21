package com.example.demo3.pojo;

import lombok.Data;

/**
 * @auther zhoupan
 * @date 2019/4/8 20:37
 * @info
 */
@Data
public class DataSource {
    String datasourceId;
    String url;
    String userName;
    String passWord;
    String code;
    String databasetype;
}
