package com.wthfeng.kurdran.render;

import com.wthfeng.kurdran.servlet.ApplicationContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/5/7 16:17
 */
public class Http500Renderer implements Renderer{

    private static final Logger LOGGER = LoggerFactory.getLogger(Http500Renderer.class);


    private Exception exception;

    public Http500Renderer(Exception exception) {
        this.exception = exception;
    }

    @Override
    public void render(ApplicationContent content) {

        HttpServletResponse response = content.getResponse();
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Renderer 500 error",e);
        }
    }
}
