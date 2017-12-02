package com.wthfeng.kurdran.server.netty;

import com.alibaba.fastjson.JSON;
import com.wthfeng.kurdran.mvc.http.HttpRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  io.netty.buffer.Unpooled;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @author wangtonghe
 * @date 2017/12/2 13:26
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
        HttpRequest request = HttpRequest.build(fullHttpRequest);
        String url = request.getUri();
        String method = request.getMethod();
        Map<String, List<String>> paramMap = request.getParamsMap();
        Map<String, String> headMap = request.getHeaders();
        boolean keepAlive = request.isKeepAlive();

        System.out.println("method = " + method);
        System.out.println("paramMap = " + paramMap);
        System.out.println("url = " + url);
        System.out.println("headMap = " + headMap);
        System.out.println("keepAlive = " + keepAlive);

        Map<String,Object> data = new HashMap<>();
        data.put("code",0);
        data.put("data","hello world");


        ByteBuf byteBuf = Unpooled.wrappedBuffer(JSON.toJSONString(data).getBytes());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"application/json;charset=UTF-8");
        if (!keepAlive) {
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            response.headers().set(HttpHeaderNames.CONNECTION, KEEP_ALIVE);
            ctx.writeAndFlush(response, ctx.voidPromise());
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("服务处理异常", cause);
        if (ctx.channel().isActive()) {
            ctx.channel().close();
        }
    }
}
