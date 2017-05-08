package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.servlet.ApplicationContent;

import javax.servlet.http.HttpServletRequest;
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
        HttpServletRequest request = content.getRequest();
        Map<String, String[]> params = request.getParameterMap();
        requestResult.setRequestParams(doParamMap(params));

        Method method = requestResult.getInvokeMethod();

        Parameter[] parameters = method.getParameters();

        //储存传来的参数
        List<Object> realParamList = new ArrayList<>(method.getParameterCount());

        Arrays.stream(parameters).forEach(p -> realParamList.add(params.get(p.getName())));

        //设置参数
        requestResult.setRealParams(realParamList.toArray());

    }

    private Map<String, Object> doParamMap(Map<String, String[]> originParamMap) {
        Map<String, Object> paramsMap = new HashMap<>();
        //只处理遇到的第一个参数
        originParamMap.forEach((name, value) -> paramsMap.put(name, value[0]));
        return paramsMap;
    }
}
