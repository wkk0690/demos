package com.example.demo3.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * 
 * @Description:拦截器配置
 * @author: zk
 * @date: 2019年9月19日 下午2:18:35
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private TestInterceptor testInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(adminInterceptor);
        registration.addPathPatterns("/**");                      //所有路径都被拦截

        InterceptorRegistration registration1 = registry.addInterceptor(testInterceptor);
        registration1.addPathPatterns("/**");
    }
}