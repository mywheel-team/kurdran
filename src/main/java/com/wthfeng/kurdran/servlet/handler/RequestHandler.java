package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.beans.Bean;
import com.wthfeng.kurdran.beans.factory.BeanFactoryImpl;
import com.wthfeng.kurdran.render.Http404Renderer;
import com.wthfeng.kurdran.servlet.ApplicationContent;
import com.wthfeng.kurdran.servlet.annotation.Action;
import com.wthfeng.kurdran.servlet.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangtonghe
 * @date 2017/4/30 23:44
 */
public class RequestHandler implements Handler {

    private  List<MethodInfo> methodInfos;


    public RequestHandler() {
        List<Bean<?>> beans = new BeanFactoryImpl().getBeans(Action.class);
        beans.forEach(bean -> {
            Class<?> clazz = bean.getBeanType();
            Method[] methods = clazz.getMethods();
            Arrays.stream(methods).forEach(m->{
                RequestMapping MthMappingAnnotation= m.getAnnotation(RequestMapping.class);
                if(MthMappingAnnotation!=null){
                    RequestMapping  reqMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
                    String prefixMapping = reqMappingAnnotation.value().length>0?reqMappingAnnotation.value()[0]:"";
                    MethodInfo methodInfo = new MethodInfo();
                    methodInfo.setInvokeMethod(m);
                    methodInfo.setMethodType(MthMappingAnnotation.method());
                    String[] reqMappings =  (String[]) Arrays.stream(MthMappingAnnotation.value()).map(e->
                        e=prefixMapping+e
                    ).collect(Collectors.toList()).toArray();
                    methodInfo.setRequestMapping(reqMappings);
                    methodInfos.add(methodInfo);
                }
            });
        });
    }

    public void handle(ApplicationContent content,RequestResult requestResult) {
        HttpServletRequest request = content.getRequest();
        Map<String,String[]> params = request.getParameterMap();
        requestResult.setRequestParams(doParamMap(params));

        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        methodInfos.forEach(info->
            Arrays.stream(info.getRequestMapping()).forEach((mappings->{
                if(mappings.equals(requestURI)){
                    Arrays.stream(info.getMethodType()).forEach((mthType->{
                       if(requestMethod.equals(mthType.name())){
                           requestResult.setInvokeMethod(info.getInvokeMethod());
                           requestResult.setRequestMapping(mappings);
                        }
                    }));
                }
            }))
        );
    }

    public Map<String,Object> doParamMap(Map<String,String[]> originParamMap){
        Map<String,Object> paramsMap = new HashMap<>();
        originParamMap.forEach((name,value)->{
            paramsMap.put(name,value[0]);
        });
        return paramsMap;
    }

}
