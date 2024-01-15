package com.example.demo.cross;

import com.example.demo.socket.client.jdk.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.WebSocketContainer;
import java.net.URI;

/**
 * @author 0X574B4B
 * @create 2018-08-13 9:44
 * @descriptions <p></p
 */

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @RequestMapping(value = "/aa")
    public String aa() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        System.out.println(request.getHeader("toke1"));
        return "success1";
    }

    @RequestMapping(value = "/bb", method = RequestMethod.POST)
    public String bb() {
        return "success2";
    }


    @Autowired
    private WebSocketClient client;

    @Autowired
    private WebSocketContainer conmtainer;

    @Resource(name = "uri4")
    private URI uri4;

    @Autowired
    private WebSocketClient jdkClient;

    @RequestMapping(value = "/test")
    public String test() throws Exception {
        client.close();
        Thread.sleep(3000);
        conmtainer.connectToServer(jdkClient, uri4);
        return "success2";
    }
}
