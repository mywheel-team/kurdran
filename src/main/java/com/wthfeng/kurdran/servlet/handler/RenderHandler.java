package com.wthfeng.kurdran.servlet.handler;

import com.wthfeng.kurdran.render.JSONRenderer;
import com.wthfeng.kurdran.render.Renderer;
import com.wthfeng.kurdran.servlet.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
        String renderName = content.getContext().getInitParameter("contentTypeRender");
        if(renderName.equals("json")){
            if(!Map.class.isAssignableFrom(resultType)){
                logger.error(" cannot resovle Map result ");
                return;
            }

            Object ret = requestResult.getRequestResult();




        }

    }
}
