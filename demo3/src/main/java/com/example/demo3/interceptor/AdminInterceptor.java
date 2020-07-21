package com.example.demo3.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo3.config.DBContextHolder;
import com.example.demo3.config.DynamicDataSource;
import com.example.demo3.pojo.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:拦截器
 * @author: zk
 * @date: 2019年9月19日 下午2:20:57
 */
@Component
public class AdminInterceptor implements  HandlerInterceptor {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("拦截器");
        try {
            String d = request.getParameter("d");
            if("1".equals(d)) {
                changeDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
 
    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }
 
    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }

    private void changeDatabase() throws Exception {
        //创建数据源
        DataSource d  = new DataSource();
        d.setDatasourceId("1");
        d.setCode("1");
        d.setUrl("jdbc:mysql://localhost:3306/test1?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        d.setUserName("root");
        d.setPassWord("123456");
        d.setDatabasetype("1");
        dynamicDataSource.createDataSourceWithCheck(d);

        //切换数据源
        DBContextHolder.setDataSource(d.getDatasourceId());
    }
}