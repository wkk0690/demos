package com.example.demo8.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 0X574B4B
 * @create 2020/06/15
 * @desc
 */
@RestController
@RequestMapping("/oauth")
@Transactional
public class OauthController {

    @RequestMapping("/redirect")
    public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        System.out.println(code);

        String path = "http://localhost";
        //转发
//        RequestDispatcher requestDispatcher=request.getRequestDispatcher(path);
//        requestDispatcher.forward(request,response);
        //重定向
        response.sendRedirect(path);
        return "123";
    }
}
