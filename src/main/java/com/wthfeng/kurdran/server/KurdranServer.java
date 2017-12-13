package com.wthfeng.kurdran.server;

import com.wthfeng.kurdran.ioc.Bean;
import com.wthfeng.kurdran.ioc.BeanFactory;
import com.wthfeng.kurdran.ioc.BeanFactoryImpl;
import com.wthfeng.kurdran.mvc.annotation.Action;
import com.wthfeng.kurdran.server.netty.NettyHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/12/2 12:43
 */
public class KurdranServer {

    private int port;

    public KurdranServer(int port) {
        this.port = port;
    }


    public void start(Class<?> startClass) throws Exception {
        initBean(startClass);
        startServer();

    }


    private void initBean(Class<?> startClass) throws Exception{
        BeanFactory beanFactory = BeanFactoryImpl.newInstance(startClass);
        List<Bean<?>> beans = beanFactory.getBeans(Action.class);



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
                    .childHandler(new NettyHandlerInitializer());
            Channel channel = server.bind().sync().channel();
            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();

        }
    }
}
