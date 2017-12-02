package com.wthfeng.kurdran.mvc;

import com.wthfeng.kurdran.mvc.http.HttpRequest;
import com.wthfeng.kurdran.mvc.http.HttpResponse;
import com.wthfeng.kurdran.render.Renderer;

/**
 * @author wangtonghe
 * @date 2017/4/30 23:37
 */
public class ApplicationContent {

    private HttpRequest request;
    private HttpResponse response;
    private Renderer renderer;

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

}
