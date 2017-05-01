package test;

import com.wthfeng.kurdran.servlet.RequestMethod;
import com.wthfeng.kurdran.servlet.annotation.Action;
import com.wthfeng.kurdran.servlet.annotation.RequestMapping;

/**
 * @author wangtonghe
 * @date 2017/5/1 17:14
 */
@Action
@RequestMapping(value = "/test",method = RequestMethod.GET)
public class HelloAction {

    @RequestMapping("/hello")
    public String  test(){
        return "hello world !";
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public String test2(String name){
        return name;
    }
}
