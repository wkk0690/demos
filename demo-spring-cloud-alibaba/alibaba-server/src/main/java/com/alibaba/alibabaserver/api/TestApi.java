package com.alibaba.alibabaserver.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "alibaba-client", fallback = CmsFallback.class)
public interface TestApi {

    @RequestMapping(value = "/test")
    String refreshToken(@RequestParam("str") String str);

    @RequestMapping(value = "/test1")
    String test1();


    @RequestMapping(value = "/findList")
    String findList();

    @RequestMapping(value = "/insert")
    String insert();

    @RequestMapping(value = "/insert1")
    String insert1();
}
