package com.wthfeng.kurdran.web.handler;

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

    /**
     * 请求结果
     */
    private Object requestResult;

    /**
     * 请求结果类型
     */
    private Class<?> requestResultType;

    public Class<?> getRequestResultType() {
        return requestResultType;
    }

    public void setRequestResultType(Class<?> requestResultType) {
        this.requestResultType = requestResultType;
    }

    public Object getRequestResult() {
        return requestResult;
    }

    public void setRequestResult(Object requestResult) {
        this.requestResult = requestResult;
    }

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
