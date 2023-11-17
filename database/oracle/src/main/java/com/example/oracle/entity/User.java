package com.example.oracle.entity;

import lombok.Data;

import java.util.Date;

/**
 * User
 * @author wkk
 * @date 2023-11-17
 */
@Data
public class User {

    private String id;
    private String username;
    private String password;
    private Date createTime;
}
