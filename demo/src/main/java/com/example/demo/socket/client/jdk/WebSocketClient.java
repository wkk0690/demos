package com.example.demo.socket.client.jdk;


import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;

/**
 * @author 0X574B4B
 * @create 2018-08-23 11:26
 * @des jdk的websocket的客户端
 */
@ClientEndpoint
@Component
public class WebSocketClient {

    private Session session;

    @OnOpen
    public void open(Session session){
        System.out.println("jdk的客户端开启了");
        this.session = session;
    }

    @OnMessage
    public void onMessage(String message){
//        JSONObject jsonObject = JSONObject.parseObject(message);
//        JSONObject data = jsonObject.getJSONObject("data");
//        String m = data.getString("m"); //买方是做币商吗
//        String q = data.getString("q"); //数量
//        String p = data.getString("p"); //价格
//        String time = data.getString("E"); //价格


        //System.out.println(message);
    }

    @OnClose
    public void onClose(){
        System.out.println("jdk的客户端关闭了");
    }


    @OnError
    public void onError(Session session, Throwable t) {
        try {
            session.close();
            t.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String message){
        this.session.getAsyncRemote().sendText(message);
    }

    public void close() throws IOException {
        if(this.session.isOpen()) this.session.close();
    }
}
