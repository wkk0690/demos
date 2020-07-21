package com.example.demo.socket.server.jdk;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author 0X574B4B
 * @create 2018-08-23 16:11
 * @descriptions <p>jdkwebsocke的服务端</p >
 */
@ServerEndpoint("/ws")
@Component
public class JdkServer {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JdkServer.class);

    //1:客户端每建立一个链接就会创建一个对象,所以静态注入https://blog.csdn.net/lotts/article/details/77944672
//    private static BibiService bibiService;
//
//    @Autowired
//    public void setBibiService(BibiService bibiService){
//        MyWebSocket.bibiService = bibiService;
//    }

//    2:构造注入也可以,必须有空参构造
//    public MyWebSocket(){
//    }
//    @Autowired
//    public MyWebSocket(BibiService bibiService){
//        this.bibiService = bibiService;
//    }

    /* 如果要解决这种线程安全的问题,不能通过线程安全的集合来保存Session解决。
     * 而应该保存整个类，并通过CopyOnWriteArraySet容器来操作
     * */
    public static CopyOnWriteArraySet<Session> sessions = new CopyOnWriteArraySet<Session>();

    //连接
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    //关闭
    @OnClose
    public void onClose(Session session) {
        try {
            sessions.remove(session);
        } catch (Exception e) {
            logger.error("onClose异常",e.getMessage());
        }
    }

    //接收消息   客户端发送过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
    }

    //异常
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            logger.error("onError发生异常!",throwable);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //统一的发送消息方法
    public synchronized static void sendMsg(Session session, String msg) {
        try {
            //1:发送字符串
            session.getBasicRemote().sendText(msg);

            //2:发送字节数组
            //byte[] compress = GZipUtils.compress(msg.getBytes());
//            if(sessions.contains(session) && session.isOpen()) {
//                session.getBasicRemote().sendObject(compress);
//                //session.getAsyncRemote().sendObject(compress);
//            }
        }catch (Exception e) {
            logger.error("发送数据异常"+e.getMessage());
            e.printStackTrace();
        }
    }
}


