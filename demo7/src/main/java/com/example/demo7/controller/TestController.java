package com.example.demo7.controller;

import com.example.demo7.utils.CookieUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wkk
 * @create 2020/04/27
 * @desc
 */
@RestController
@RequestMapping("/demo7")
public class TestController {

    @RequestMapping("/test1")
    public String test1(HttpServletRequest request, HttpServletResponse response, String value){
        Cookie cookie = new Cookie("testCookie", value);
        cookie.setDomain("wkk.life");
        cookie.setPath("/");
        cookie.setMaxAge(36000);
        response.addCookie(cookie);
        return "123";
    }
}
