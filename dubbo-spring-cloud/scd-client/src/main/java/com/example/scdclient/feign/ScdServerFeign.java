package com.example.scdclient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wkk
 * @create 2020/07/27
 * @desc
 */
@FeignClient("scd-server")
public interface ScdServerFeign {

    @GetMapping("/user/test")
    String test(@RequestParam("num") int num);
}
