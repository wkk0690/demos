package com.example.demo.socket.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 0X574B4B
 * @create 2018-08-15 10:12
 * @descriptions <p>netty的socket的服务端</p >
 */
@Component
public class NettyServer {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Autowired
    private NettyServerHandler handler;

    public void start() {
        try {
            NioEventLoopGroup bossGroup = new NioEventLoopGroup(1); //用来监控tcp链接;执行server.accept()操作
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(); //用来处理io事件;默认线程数是 cpu核心数

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //将字节解码为HttpRequest，HttpContent，LastHttpContent.Encode HttpRequest，HttpContent，LastHttpContent to bytes。
                            pipeline.addLast(new HttpServerCodec());

                            //此ChannelHandler将HttpMessage及其后续HttpContents聚合到单个FullHttpRequest或FullHttpResponse中（取决于它是否用于处理请求或响应）。如果安装了此，则管道中的下一个ChannelHandler将仅接收完整的HTTP请求。
                            pipeline.addLast(new HttpObjectAggregator(64 * 1024));

                            // Write the contents of a file.
                            //pipeline.addLast(new ChunkedWriteHandler());

                            //处理FullHttpRequests（未发送到“/ ws”URI的那些）。
                            //pipeline.addLast(new HttpRequestHandler());

                            //根据WebSockets规范的要求，处理WebSocket升级握手，PingWebSocketFrames，PongWebSocketFrames和CloseWebSocketFrames。
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
                            //构造函数中readerIdleTime为读超时时间，writerIdleTime为写超时时间，allIdleTime为所有类型的超时时间。
                            pipeline.addLast(new IdleStateHandler(6, 0, 0, TimeUnit.SECONDS));
                            // 字符串 解码 和 编码
                            //                         pipeline.addLast(new StringDecoder());//解码
                            //                         pipeline.addLast(new StringEncoder());//编码
                            pipeline.addLast(handler);
                        }
                    });

            Map<ChannelOption<?>, Object> tcpChannelOptions = new HashMap<ChannelOption<?>, Object>();

            //当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
            tcpChannelOptions.put(ChannelOption.SO_KEEPALIVE, true);

            //服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
            tcpChannelOptions.put(ChannelOption.SO_BACKLOG, 100);

            //比如，某个服务器进程占用了TCP的80端口进行监听，此时再次监听该端口就会返回错误，使用该参数就可以解决问题，该参数允许共用该端口，这个在服务器程序中比较常使用，
            //比如某个进程非正常退出，该程序占用的端口可能要被占用一段时间才能允许其他进程使用，而且程序死掉以后，内核一需要一定的时间才能够释放此端口，不设置SO_REUSEADDR
            //就无法正常使用该端口。
            //tcpChannelOptions.put(ChannelOption.SO_REUSEADDR,true);

            //这两个参数用于操作接收缓冲区和发送缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功，发送缓冲区用于保存发送数据，直到发送成功。
            // tcpChannelOptions.put(ChannelOption.SO_SNDBUF,true);
            // tcpChannelOptions.put(ChannelOption.SO_RCVBUF,true);

            //Linux内核默认的处理方式是当用户调用close（）方法的时候，函数返回，在可能的情况下，尽量发送数据，不一定保证
            //会发生剩余的数据，造成了数据的不确定性，使用SO_LINGER可以阻塞close()的调用时间，直到数据完全发送
            //tcpChannelOptions.put(ChannelOption.SO_LINGER,true);

            //该参数的使用与Nagle算法有关Nagle算法是将小的数据包组装为更大的帧然后进行发送，而不是输入一次发送一次,因此在数据包不足的时候会等待其他数据的到了，
            // 组装成大的数据包进行发送，虽然该方式有效提高网络的有效负载，但是却造成了延时，而该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输，
            // 于TCP_NODELAY相对应的是TCP_CORK，该选项是需要等到发送的数据量最大的时候，一次性发送数据，适用于文件传输。
            //tcpChannelOptions.put(ChannelOption.TCP_NODELAY,true);

            Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
            for (ChannelOption option : keySet) {
                b.option(option, tcpChannelOptions.get(option));
            }
            b.bind(new InetSocketAddress(Integer.parseInt("8111"))).sync().channel().closeFuture().sync().channel();
        } catch (Exception e) {
            logger.error("服务端启动异常", e);
        }
    }
}
