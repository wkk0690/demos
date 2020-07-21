package com.example.demo.socket.server.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 0X574B4B
 * @create 2018-06-25 18:16
 * @desc netty的websocket服务端处理器
 */
@Component
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    //public static CopyOnWriteArraySet<Session> channels = new CopyOnWriteArraySet<Session>();

    //接受信息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        if (msg.text().equals("helloworld")) {
            System.out.println(msg.text());
            Map<String, String> map = new HashMap<>();
            map.put("jack", "12");
            ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(map)));
        }
    }

    //添加
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());
    }

    //移除用户
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        channels.remove(channel);
    }

    //上线
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
    }

    //下线
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel incoming = ctx.channel();
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    //利用读空闲发送心跳检测消息
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) { //5s读不到,移除
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                //throw new Exception("Idle exception");
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    public static void sendMsg(Channel channel, String msg) {
        try {
            //1:发送字符串
            channel.writeAndFlush(msg);

            //2:发送字节数组
//            byte[] compress = GZipUtils.compress(msg.getBytes());
//            channel.writeAndFlush(new BinaryWebSocketFrame(Unpooled.wrappedBuffer(compress)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}