package com.wthfeng.kurdran.web.handler;


import com.wthfeng.kurdran.mvc.http.HttpMethod;

import java.lang.reflect.Method;

/**
 * 方法信息，主要为注解信息
 * @author wangtonghe
 * @date 2017/5/7 12:40
 */
public class MethodInfo {

    private Method invokeMethod;

    private String[] requestMapping;

    private HttpMethod methodType;


    public Method getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(Method invokeMethod) {
        this.invokeMethod = invokeMethod;
    }

    public String[] getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String[] requestMapping) {
        this.requestMapping = requestMapping;
    }

    public HttpMethod getMethodType() {
        return methodType;
    }

    public void setMethodType(HttpMethod methodType) {
        this.methodType = methodType;
    }
}
