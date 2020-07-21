package com.example.demo3.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wkk
 * @create 2020/07/20
 * @desc
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String id;
    private String username;
    private String password;
}
