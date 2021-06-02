package com.wthfeng.kurdran.web.server;

import com.wthfeng.kurdran.Kurdran;
import com.wthfeng.kurdran.server.netty.NettyHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @author wangtonghe
 * @date 2017/12/2 12:43
 */
public class KurdranServer {

    private Kurdran kurdran;

    private int port;


    public KurdranServer(Kurdran kurdran) {
        this.kurdran = kurdran;
        this.port = kurdran.port;

    }


    public void start(Class<?> startClass) throws Exception {
        initBean(startClass);  //初始化bean
        startServer();

    }


    private void initBean(Class<?> startClass) throws Exception{



    }


    private void startServer() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        try {
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new NettyHandlerInitializer(kurdran));
            Channel channel = server.bind().sync().channel();
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();

        }
    }
}
