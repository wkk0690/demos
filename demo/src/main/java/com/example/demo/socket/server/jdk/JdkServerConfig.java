package com.example.demo.socket.server.jdk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author 0X574B4B
 * @create 2018-08-23 16:13
 * @descriptions <p>jdkwebsocket的配置</p >
 */
@Configuration
public class JdkServerConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
