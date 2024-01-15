package com.example.demo.socket.client.jdk;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * @Package: main.java.xyz.zouzheng.web_socket$
 * @ClassName: ClientTest$
 * @Author: zhangchao
 * @CreateDate: 2019-05-13$
 * @Version: V1.1.0
 *
 * <dependency>
 * <groupId>org.java-websocket</groupId>
 * <artifactId>Java-WebSocket</artifactId>
 * <version>1.3.8</version>
 * </dependency>
 */
@Slf4j
public class ClientTest {

    public static void main(String[] args) throws Exception {
//        String url = "ws://127.0.0.1:6003/api/shake?token='C5AA320520A64DE6BAF106D88F478B636EBAECDB64F0412AB166782BB6CC4666'";
        String url = "ws://www.hkbiku.com/vct-ws/webSocket";

        URI uri = new URI(url);

        WebSocketClient webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                System.out.println("open");
            }

            @Override
            public void onMessage(String s) {
                System.out.println(s);
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                System.out.println("close  ----");
                System.out.println(i);
                System.out.println(s);
                System.out.println(b);
                System.out.println("close  ----");
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e.getMessage());
            }
        };
        webSocketClient.connect();
        System.out.println("连接ing...");
        System.out.println(webSocketClient.getDraft());

        /**
         * 0 ：对应常量CONNECTING (numeric value 0)，
         * 正在建立连接连接，还没有完成。The connection has not yet been established.
         * 1 ：对应常量OPEN (numeric value 1)，
         * 连接成功建立，可以进行通信。The WebSocket connection is established and communication is possible.
         * 2 ：对应常量CLOSING (numeric value 2)
         * 连接正在进行关闭握手，即将关闭。The connection is going through the closing handshake.
         * 3 : 对应常量CLOSED (numeric value 3)
         * 连接已经关闭或者根本没有建立。The connection has been closed or could not be opened.
         */
        while (!webSocketClient.getReadyState().equals("1")) {
            log.info("正在连接...");
        }
    }
}
