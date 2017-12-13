package com.wthfeng.kurdran.test.action;

import com.wthfeng.kurdran.mvc.annotation.Action;
import com.wthfeng.kurdran.mvc.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangtonghe
 * @date 2017/12/13 21:45
 */
@Action
@RequestMapping(value = "/test")
public class HelloAction {

    @RequestMapping(value = "/hello")
    public Map<String,Object> hello(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("data","hello,world");
        return result;
    }

}
