package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.servlet.RequestMethod;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 方法信息，主要为注解信息
 * @author wangtonghe
 * @date 2017/5/7 12:40
 */
public class MethodInfo {

    private Method invokeMethod;

    private String[] requestMapping;

    private RequestMethod[] methodType;


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

    public RequestMethod[] getMethodType() {
        return methodType;
    }

    public void setMethodType(RequestMethod[] methodType) {
        this.methodType = methodType;
    }
}
