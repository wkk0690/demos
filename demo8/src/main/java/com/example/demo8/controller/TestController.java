package com.example.demo8.controller;

import com.example.demo8.utils.CookieUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wkk
 * @create 2020/04/27
 * @desc
 */
@RestController
@RequestMapping("/demo8")
public class TestController {

    @RequestMapping("/test1")
    public String test1(HttpServletRequest request){
        return CookieUtils.getCookieValue(request, "testCookie");
    }

    @RequestMapping("/test2")
    public String test2(HttpServletRequest request){
        return CookieUtils.getCookieValue(request, "accessToken");
    }

    @RequestMapping("/test3")
    public String test3(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "---" + cookie.getValue());
        }
        return "123";
    }
}
