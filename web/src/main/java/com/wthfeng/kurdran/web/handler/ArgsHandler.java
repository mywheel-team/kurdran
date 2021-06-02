package com.wthfeng.kurdran.web.handler;

import com.wthfeng.kurdran.mvc.ApplicationContent;
import com.wthfeng.kurdran.mvc.http.HttpRequest;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * 参数处理器
 *
 * @author wangtonghe
 * @date 2017/5/7 15:32
 */
public class ArgsHandler implements Handler {



    @Override
    public void handle(ApplicationContent content, RequestResult requestResult) {

        //处理传来的参数
        HttpRequest request = content.getRequest();
        Map<String, List<String>> params = request.getParamsMap();
        requestResult.setRequestParams(doParamMap(params));

        Method method = requestResult.getInvokeMethod();

        Parameter[] parameters = method.getParameters();

        //储存传来的参数
        List<Object> realParamList = new ArrayList<>(method.getParameterCount());

        Arrays.stream(parameters).forEach(p -> realParamList.add(params.get(p.getName()).get(0)));

        //设置参数
        requestResult.setRealParams(realParamList.toArray());

    }

    private Map<String, Object> doParamMap(Map<String, List<String>> originParamMap) {
        Map<String, Object> paramsMap = new HashMap<>();
        //只处理遇到的第一个参数
        originParamMap.forEach((name, value) -> paramsMap.put(name, value.get(0)));
        return paramsMap;
    }
}
