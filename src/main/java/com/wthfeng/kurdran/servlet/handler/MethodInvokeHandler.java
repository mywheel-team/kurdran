package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.servlet.ApplicationContent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 执行方法处理器
 *
 * @author wangtonghe
 * @date 2017/5/8 18:36
 */
public class MethodInvokeHandler implements Handler {

    @Override
    public void handle(ApplicationContent content, RequestResult requestResult) {
        Method method = requestResult.getInvokeMethod();
        Class<?> resultType = method.getReturnType();
        Class clazz = method.getDeclaringClass();
        try {
            Arrays.stream(requestResult.getRealParams()).forEach(System.out::println);
            Object resultObj = method.invoke(clazz.newInstance(), requestResult.getRealParams());
            requestResult.setRequestResult(resultObj);
            requestResult.setRequestResultType(resultType);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
