package com.wthfeng.kurdran.servlet.handler;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 请求处理结果类
 * @author wangtonghe
 * @date 2017/5/7 11:52
 */
public class RequestResult {

    /**
     * 处理对应方法体
     */
    private Method invokeMethod;

    /**
     * 匹配的url
     */
    private String requestMapping;

    /**
     * 请求参数集合
     */
    private Map<String,Object> requestParams;

    /**
     * 参数列表
     */
    private Object[] realParams;


    public Object[] getRealParams() {
        return realParams;
    }

    public void setRealParams(Object[] realParams) {
        this.realParams = realParams;
    }

    public String getRequestMapping() {
        return requestMapping;
    }

    public void setRequestMapping(String requestMapping) {
        this.requestMapping = requestMapping;
    }

    public Method getInvokeMethod() {
        return invokeMethod;
    }

    public void setInvokeMethod(Method invokeMethod) {
        this.invokeMethod = invokeMethod;
    }


    public Map<String, Object> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Map<String, Object> requestParams) {
        this.requestParams = requestParams;
    }
}
