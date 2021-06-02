//package com.wthfeng.kurdran.web.server.netty;
//
//import com.alibaba.fastjson.JSON;
//import com.wthfeng.kurdran.Kurdran;
//import com.wthfeng.kurdran.mvc.handler.MethodInfo;
//import com.wthfeng.kurdran.mvc.http.HttpRequest;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.handler.codec.http.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.util.*;
//import java.util.stream.Collectors;
//
//import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
//
///**
// * @author wangtonghe
// * @date 2017/12/2 13:26
// */
//public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
//
//    private Kurdran kurdran;
//
//    public List<MethodInfo> methodInfos = new ArrayList<>();
//
//
//    private Logger logger = LoggerFactory.getLogger(HttpServerHandler.class);
//
//    public HttpServerHandler(Kurdran kurdran) {
//        this.kurdran = kurdran;
//        this.methodInfos = kurdran.methodInfos;
//    }
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest fullHttpRequest) throws Exception {
//        HttpRequest request = HttpRequest.build(fullHttpRequest);
//        String url = request.getUri();
//        String method = request.getMethod();
//        Map<String, List<String>> paramMap = request.getParamsMap();
//        Map<String, String> headMap = request.getHeaders();
//        boolean keepAlive = request.isKeepAlive();
//
//        System.out.println("url = " + url);
//        System.out.println("headMap = " + headMap);
//        System.out.println("keepAlive = " + keepAlive);
//
//        HttpRequest httpRequest = HttpRequest.build(fullHttpRequest);  //构造请求
//        MethodInfo methodInfo = getMethodByList(httpRequest.getUri(), httpRequest.getMethod());  //路由查找方法
//        Object result = invokeMethod(methodInfo, request);  //执行方法
//
//
//        ByteBuf byteBuf = Unpooled.wrappedBuffer(JSON.toJSONString(result).getBytes());
//        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
//
//        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json;charset=UTF-8");
//        if (!keepAlive) {
//            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
//        } else {
//            response.headers().set(HttpHeaderNames.CONNECTION, KEEP_ALIVE);
//            ctx.writeAndFlush(response, ctx.voidPromise());
//        }
//
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        logger.error("服务处理异常", cause);
//        if (ctx.channel().isActive()) {
//            ctx.channel().close();
//        }
//    }
//
//
//    private MethodInfo getMethodByList(String url, String httpMethod) throws Exception {
//
//        List<MethodInfo> list = methodInfos.stream().filter(info -> info.getMethodType().name().equals(httpMethod)).collect(Collectors.toList());
//        for (MethodInfo methodInfo : list) {
//            String[] mappings = methodInfo.getRequestMapping();
//            for (String mapping : mappings) {
//                if (mapping.equals(url)) {
//                    return methodInfo;
//                }
//            }
//        }
//        return null;
//    }
//
//
//    private Object invokeMethod(MethodInfo methodInfo, HttpRequest request) {
//        Method method = methodInfo.getInvokeMethod();
//        Class<?> resultType = method.getReturnType();
//        Class clazz = method.getDeclaringClass();
//        Object[] params = handleArg(methodInfo, request);
//        Object resultObj = null;
//        try {
//            resultObj = method.invoke(clazz.newInstance(), params);
//
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        }
//        return resultObj;
//
//
//    }
//
//    private Object[] handleArg(MethodInfo methodInfo, HttpRequest request) {
//
//        //处理传来的参数
//        Map<String, List<String>> params = request.getParamsMap();
//
//        Method method = methodInfo.getInvokeMethod();
//
//
//        Parameter[] parameters = method.getParameters();
//
//
//        Arrays.stream(parameters).forEach(p-> System.out.println(p.getName()));
//
//        //储存传来的参数
//        List<Object> realParamList = new ArrayList<>(method.getParameterCount());
//
//        Arrays.stream(parameters).forEach(p -> realParamList.add(params.get(p.getName()).get(0)));
//
//        return realParamList.toArray();
//    }
//}
