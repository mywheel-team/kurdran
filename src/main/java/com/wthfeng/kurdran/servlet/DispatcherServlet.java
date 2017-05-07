package com.wthfeng.kurdran.servlet;

import com.wthfeng.kurdran.render.Http404Renderer;
import com.wthfeng.kurdran.render.Http500Renderer;
import com.wthfeng.kurdran.render.Renderer;
import com.wthfeng.kurdran.servlet.handler.ArgsHandler;
import com.wthfeng.kurdran.servlet.handler.Handler;
import com.wthfeng.kurdran.servlet.handler.RequestHandler;
import com.wthfeng.kurdran.servlet.handler.RequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/4/30 22:47
 */
public class DispatcherServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestHandler requestHandler;

    private ArgsHandler argsHandler;


    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.debug("servlet init start ...");
        initStrategies();


    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContent content = new ApplicationContent();
        content.setRequest(req);
        content.setResponse(resp);
        doDispatch(content);

    }

    private void initStrategies() {
        requestHandler = new RequestHandler();
        argsHandler = new ArgsHandler();

    }


    private void doDispatch(ApplicationContent content) {

        RequestResult requestResult = new RequestResult();
        try {
            requestHandler.handle(content, requestResult);
            if (requestResult.getRequestMapping() != null) {
                argsHandler.handle(content,requestResult);

            }

        }catch (Exception e){
            content.setRenderer(new Http500Renderer());
        }
        doResult(content);

    }

    /**
     * 处理响应
     * @param content 上下文
     */
    private void doResult(ApplicationContent content){
        Renderer renderer = content.getRenderer();
        if(renderer==null){
            renderer = new Http404Renderer();
        }
        renderer.render(content);

    }


}
