package com.example.demo.socket.client.jdk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 0X574B4B
 * @create 2018-8/23
 * @desc jdk的websocket配置
 */
@Configuration
//@EnableWebSocketMessageBroker
public class WsConfig {

    private String url2 = "wss://stream2.binance.cloud/stream?streams=btcusdt@depth5"; //深度
    private String url3 = "wss://stream2.binance.cloud/stream?streams=btcusdt@trade"; //实时成交
    private String url4 = "wss://stream2.binance.cloud/stream?streams=!miniTicker@arr@3000ms/btcusdt@depth.b10/btcusdt@aggTrade.b10"; //实时成交

    @Bean("bianDepthURI")
    public URI getURI2() throws URISyntaxException {
        return new URI(url2);
    }

    @Bean("bianTradeURI")
    public URI getURI3() throws URISyntaxException {
        return new URI(url3);
    }
    @Bean("uri4")
    public URI getURI4() throws URISyntaxException {
        return new URI(url4);
    }

    @Bean
    public WebSocketContainer getWebSocketContainer(){
        return ContainerProvider.getWebSocketContainer();
    }
}
