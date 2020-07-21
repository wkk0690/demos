package com.example.demo8.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author wkk
 * @create 2020/06/15
 * @desc
 */
@Data
@Builder
public class User {
    private String id;
    private String username;
    private String password;
    private Date createTime;
}
