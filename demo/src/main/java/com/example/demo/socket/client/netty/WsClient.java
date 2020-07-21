package com.example.demo.socket.client.netty;

import com.alibaba.fastjson.JSONObject;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.ssl.SslContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * @author 0X574B4B
 * @create 2018/8/14
 * @desc netty连接火币的客户端
 */
@Component
public class WsClient {
	private static final Logger logger = LoggerFactory.getLogger(WsClient.class);

	private Channel channel = null;
	private EventLoopGroup group = null;
	private ChannelFuture future = null;
	private Map<String,String> subMap = new HashMap<>();
	private boolean isAlive = false;

	@Autowired
	private MoniterTask moniter;
	@Resource(name = "huobiURI")
	private URI uri;

	public void start() {
		this.connect();
		new Timer().schedule(moniter, 1000, 5000);
	}

	private void connect() {
		try {
			group = new NioEventLoopGroup(1);
			Bootstrap bootstrap = new Bootstrap();
			final SslContext sslCtx = SslContext.newClientContext();
			final WsClientHandler handler = new WsClientHandler(WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders(), Integer.MAX_VALUE), moniter, this);
			bootstrap.group(group).option(ChannelOption.TCP_NODELAY, true).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				protected void initChannel(SocketChannel ch) {
					ChannelPipeline p = ch.pipeline();
					if (sslCtx != null) {
						p.addLast(sslCtx.newHandler(ch.alloc(), uri.getHost(), uri.getPort()));
					}
					p.addLast(new HttpClientCodec(), new HttpObjectAggregator(8192), handler);
				}
			});

			future = bootstrap.connect(uri.getHost(), 443);
			future.addListener(new ChannelFutureListener() { //io结束的回调
				public void operationComplete(final ChannelFuture future) throws Exception {
				}
			});
			channel = future.sync().channel();
			handler.handshakeFuture().sync();
			this.setStatus(true);
		} catch (Exception e) {
			logger.error("wsclient connect error ", e);
			e.printStackTrace();
			group.shutdownGracefully();//优雅退出
			this.setStatus(false);
		}
	}

	public void sendMessage(String message) {
		if (channel == null) return;
		channel.writeAndFlush(new TextWebSocketFrame(message));
	}

	public void setStatus(boolean flag) {
		this.isAlive = flag;
	}

	//发送订阅
	public void addSub(String sub, String subId) {
		if (channel == null) return;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sub", sub);
		jsonObject.put("id", subId);
		String msg = jsonObject.toJSONString();
		this.sendMessage(msg);
		subMap.put(sub,msg);
	}

	//发送请求
	public void addReq(String req, String subId) {
		if (channel == null) return;
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("req", req);
		jsonObject.put("id", subId);
		String msg = jsonObject.toJSONString();
		this.sendMessage(msg);
	}

	public void addChannel(String msg) {
		if (channel == null) {
			return;
		}
		this.sendMessage(msg);
		subMap.put(JSONObject.parseObject(msg).getString("sub"),msg);
	}

	public void reConnect() throws Exception {
		this.group.shutdownGracefully();
		this.group = null;
		this.connect();
		if (future.isSuccess()) {
			this.setStatus(true);
			for (Map.Entry<String, String> entry : subMap.entrySet()) {
				this.addChannel(entry.getValue());
			}
		}
	}
}
