package com.example.scdclient.feign;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
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
@DubboTransported(protocol = "dubbo")
public interface ScdServerFeign1 {

    @GetMapping("/echo")
    public String params(@RequestParam("msg") String msg);
}
