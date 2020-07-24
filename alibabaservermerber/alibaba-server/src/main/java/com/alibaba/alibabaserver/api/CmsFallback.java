package com.alibaba.alibabaserver.api;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CmsFallback implements TestApi{

    @Override
    public String refreshToken(String oldToken) {
        return "异常了......";
    }

    @Override
    public String test1() {
        return "与i长了";
    }

    @Override
    public String findList() {
        return null;
    }

    @Override
    public String insert() {
        return null;
    }

    @Override
    public String insert1() {
        return null;
    }
}
