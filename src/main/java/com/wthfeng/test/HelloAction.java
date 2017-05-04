package com.wthfeng.test;

import com.wthfeng.kurdran.servlet.RequestMethod;
import com.wthfeng.kurdran.servlet.annotation.Action;
import com.wthfeng.kurdran.servlet.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * @author wangtonghe
 * @date 2017/5/1 17:14
 */
@Action
@RequestMapping(value = "/test", method = RequestMethod.GET)
public class HelloAction {

    @Inject
    private HelloService helloService;



    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String test2(String name) {

        return name;
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello() {
        String name = helloService.getHello();
        return "hello " + name;
    }
}
