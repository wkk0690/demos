package com.example.demo.socket.client.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.GZipUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0X574B4B
 * @create 2018/8/14
 * @desc netty连接火币的客户端处理器
 */
public class WsClientHandler extends SimpleChannelInboundHandler<Object> {
    private static final Logger logger = LoggerFactory.getLogger(WsClientHandler.class);

    private ChannelPromise handshakeFuture;
    private WebSocketClientHandshaker handshaker;
    private MoniterTask moniter;
    private WsClient client;

    public WsClientHandler(WebSocketClientHandshaker handshaker, MoniterTask moniter, WsClient client) {
        this.handshaker = handshaker;
        this.moniter = moniter;
        this.client = client;
    }

    public ChannelFuture handshakeFuture() {
        return handshakeFuture;
    }

    /*
     * (non-Javadoc)
     * 覆盖了 handlerAdded() 事件处理方法。
     * 每当从服务端收到新的客户端连接时
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) {
        handshakeFuture = ctx.newPromise();
    }

    /*
     * 覆盖channelActive 方法在channel被启用的时候触发（在建立连接的时候）
     * 覆盖了 channelActive() 事件处理方法。服务端监听到客户端活动
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        handshaker.handshake(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.error("WebSocket Client disconnected!");
    }

    /*
     * 覆盖了 channelRead0() 事件处理方法。
     * 每当从服务端读到客户端写入信息时，
     * 其中如果你使用的是 Netty 5.x 版本时，
     * 需要把 channelRead0() 重命名为messageReceived()
     *
     * 每当接受到数据时调用,少量数据也可能调用多次(用的是ByteBuf接受的,这是一个面向流的协议)
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel ch = ctx.channel();
        moniter.updateTime();
        if (!handshaker.isHandshakeComplete()) {
            handshaker.finishHandshake(ch, (FullHttpResponse) msg);
            logger.error("WebSocket Client connected!");
            handshakeFuture.setSuccess();
            return;
        }

        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            throw new IllegalStateException("Unexpected FullHttpResponse (getStatus=" + response.getStatus()
                    + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
        }

        WebSocketFrame frame = (WebSocketFrame) msg;
        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binaryFrame = (BinaryWebSocketFrame) frame;
            onReceive(decodeByteBuff(binaryFrame.content()));
        } else if (frame instanceof PongWebSocketFrame) {
            logger.info("WebSocket Client received pong");
        } else if (frame instanceof CloseWebSocketFrame) {
            logger.error("WebSocket Client received closing");
            ch.close();
        }
    }

    /*
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，
     * 即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。
     * 在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。
     * 然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，
     * 比如你可能想在关闭连接之前发送一个错误码的响应消息。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        if (!handshakeFuture.isDone()) {
            handshakeFuture.setFailure(cause);
        }
        ctx.close();
    }

    public JSONObject decodeByteBuff(ByteBuf buf) throws Exception {

        byte[] temp = new byte[buf.readableBytes()];
        buf.readBytes(temp);

        temp = GZipUtils.decompress(temp);
        String str = new String(temp, "UTF-8");
        if (str.contains("ping")) {
            client.sendMessage(str.replace("ping", "pong"));
        }
        JSONObject json = JSON.parseObject(str);

        return json;
    }

    //处理数据
    public void onReceive(JSONObject json) {
        try {
            //System.out.println(json.toJSONString());
        } catch (Exception e) {
            logger.error("处理数据异常");
            e.printStackTrace();
        }
    }
}