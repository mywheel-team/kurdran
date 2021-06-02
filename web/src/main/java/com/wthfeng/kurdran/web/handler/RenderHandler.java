package com.wthfeng.kurdran.web.handler;

import com.wthfeng.kurdran.mvc.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理结果handler
 *
 * @author wangtonghe
 * @date 2017/5/13 16:59
 */
public class RenderHandler implements Handler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handle(ApplicationContent content, RequestResult requestResult) {
        Class<?> resultType = requestResult.getRequestResultType();


    }
}
