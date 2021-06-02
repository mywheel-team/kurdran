package com.wthfeng.kurdran.web.handler;

import com.wthfeng.kurdran.ioc.Bean;
import com.wthfeng.kurdran.ioc.BeanFactoryImpl;
import com.wthfeng.kurdran.mvc.ApplicationContent;
import com.wthfeng.kurdran.mvc.annotation.Action;
import com.wthfeng.kurdran.mvc.annotation.RequestMapping;
import com.wthfeng.kurdran.mvc.http.HttpRequest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangtonghe
 * @date 2017/4/30 23:44
 */
public class RequestHandler implements Handler {

    private List<MethodInfo> methodInfos = new ArrayList<>();


    private String contextPath;


    public RequestHandler() {
        List<Bean<?>> beans = new BeanFactoryImpl().getBeans(Action.class);
        beans.forEach(bean -> {
            Class<?> clazz = bean.getBeanType();
            Method[] methods = clazz.getMethods();
            Arrays.stream(methods).forEach(m -> {
                RequestMapping MthMappingAnnotation = m.getAnnotation(RequestMapping.class);
                if (MthMappingAnnotation != null) {
                    RequestMapping reqMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
                    String prefixMapping = reqMappingAnnotation.value().length > 0 ? reqMappingAnnotation.value()[0] : "";
                    MethodInfo methodInfo = new MethodInfo();
                    methodInfo.setInvokeMethod(m);
                    methodInfo.setMethodType(MthMappingAnnotation.method());
                    List<String> mappingList = Arrays.stream(MthMappingAnnotation.value()).map(e ->
                            prefixMapping + e
                    ).collect(Collectors.toList());

                    String[] reqMappings = mappingList.toArray(new String[0]);
                    methodInfo.setRequestMapping(reqMappings);
                    methodInfos.add(methodInfo);
                }
            });
        });
    }

    /**
     * 获取请求对应的方法
     *
     * @param content       上下文
     * @param requestResult 保存请求结果的类
     */
    public void handle(ApplicationContent content, RequestResult requestResult) {
        HttpRequest request = content.getRequest();
        String requestURI = request.getUri();

        System.out.println(request);
        String requestMethod = request.getMethod();
        contextPath = request.getContextPath();
        //查找与请求url对应的方法并存入结果类
        methodInfos.forEach(info ->
                Arrays.stream(info.getRequestMapping()).forEach((mapping -> {
                    String requestMapping = contextPath + mapping;
                    if (requestMapping.equals(requestURI)) {
                        if (requestMethod.equals(info.getInvokeMethod())) {
                            requestResult.setInvokeMethod(info.getInvokeMethod());
                            requestResult.setRequestMapping(requestMapping);
                        }
                    }
                }))
        );
    }

}
