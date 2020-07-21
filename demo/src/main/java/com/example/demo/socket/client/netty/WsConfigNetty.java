package com.example.demo.socket.client.netty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 0X574B4B
 * @create 2018-8/23
 * @desc netty的客户端配置
 */
@Configuration
//@EnableWebSocketMessageBroker
public class WsConfigNetty {

    private String url1 = "wss://hb.bao.top/ws";

    @Bean("huobiURI")
    public URI getURI1() throws URISyntaxException {
        return new URI(url1);
    }
}
