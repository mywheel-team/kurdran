package com.wthfeng.kurdran.render;

import com.wthfeng.kurdran.servlet.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/5/7 15:51
 */
public class Http404Renderer implements Renderer {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void render(ApplicationContent content) {
        HttpServletResponse response = content.getResponse();
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("renfer 404 error",e);
        }

    }
}
