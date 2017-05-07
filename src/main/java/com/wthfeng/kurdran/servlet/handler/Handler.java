package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.servlet.ApplicationContent;

/**
 * @author wangtonghe
 * @date 2017/5/1 18:48
 */
public interface Handler {

    void handle(final  ApplicationContent content,final RequestResult requestResult);

}
