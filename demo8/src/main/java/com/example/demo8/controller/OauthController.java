package com.example.demo8.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo8.dao.UserDao;
import com.example.demo8.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
