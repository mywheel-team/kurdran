package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.servlet.ApplicationContent;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/5/7 15:32
 */
public class ArgsHandler implements Handler {

    private Map<String,String> argsMap = new HashMap<>();


    @Override
    public void handle(ApplicationContent content, RequestResult requestResult) {

       Method method = requestResult.getInvokeMethod();
//       method.getAnnotation()


    }
}
