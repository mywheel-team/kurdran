package com.wthfeng.kurdran.mvc.handler;

import com.wthfeng.kurdran.mvc.ApplicationContent;

/**
 * @author wangtonghe
 * @date 2017/5/1 18:48
 */
public interface Handler {

    void handle(final  ApplicationContent content,final RequestResult requestResult);

}
