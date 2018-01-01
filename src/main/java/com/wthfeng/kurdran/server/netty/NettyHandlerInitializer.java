package com.wthfeng.kurdran.server.netty;

import com.wthfeng.kurdran.Kurdran;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;


/**
 * @author wangtonghe
 * @date 2017/12/2 12:56
 */
public class NettyHandlerInitializer extends ChannelInitializer<SocketChannel> {

    public NettyHandlerInitializer(Kurdran kurdran) {
        this.kurdran = kurdran;
    }

    private Kurdran kurdran;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline cp = socketChannel.pipeline();
        cp.addLast(new HttpServerCodec());  //添加服务端http编、解码器
        cp.addLast(new HttpObjectAggregator(512*1024));  //http消息聚合
        cp.addLast(new HttpContentCompressor());   //开启压缩
        cp.addLast(new HttpServerHandler(kurdran));
    }
}
