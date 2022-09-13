package com.example.demo8.controller;

import com.example.demo8.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author 0X574B4B
 * @create 2020/04/27
 * @desc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/demo8")
public class TestController {

    @Value("${test.test4.aa}")
//    private final String aa;
    private String aa;

    @RequestMapping("/test4")
    public String test4() {
        return aa;
    }

    public static void main(String[] args) {
//        TestController t = new TestController("123");
    }

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
