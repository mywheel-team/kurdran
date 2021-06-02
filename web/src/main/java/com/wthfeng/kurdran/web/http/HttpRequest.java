package com.wthfeng.kurdran.web.http;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/12/2 12:29
 */
public class HttpRequest implements IHttpRequest {

    private String method;

    private Map<String, String> headers = new HashMap<>();

    private String uri;

    private String contextPath;

    private Map<String, List<String>> paramsMap = new HashMap<>();

    private boolean keepAlive;


    private HttpRequest() {

    }

    /**
     * 构建http 请求参数
     * @param fullHttpRequest
     * @return
     * @throws Exception
     */
    public static HttpRequest build(FullHttpRequest fullHttpRequest) throws Exception {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
        httpRequest.uri = fullHttpRequest.uri();
        fullHttpRequest.headers().forEach(entry ->
                httpRequest.headers.put(entry.getKey(), entry.getValue())
        );
        httpRequest.method = fullHttpRequest.method().name();
        if (httpRequest.method.equals(HttpMethod.GET.name())) {  //GET请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
            decoder.parameters().forEach((k, v) -> httpRequest.paramsMap.put(k, v));
        } else if (httpRequest.method.equals(HttpMethod.POST.name())) {

            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(fullHttpRequest);// POST请求

            List<InterfaceHttpData> paramList = decoder.getBodyHttpDatas();
            paramList.forEach(httpRequest::parsePostParam);
        }
        return httpRequest;


    }

    private  void parsePostParam(InterfaceHttpData data) {
        try {
            switch (data.getHttpDataType()) {
                case Attribute:
                    Attribute param = (Attribute) data;
                    this.paramsMap.put(param.getName(), Collections.singletonList(param.getValue()));
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            data.release();
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Map<String, List<String>> getParamsMap() {
        return paramsMap;
    }

    public void setParamsMap(Map<String, List<String>> paramsMap) {
        this.paramsMap = paramsMap;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
