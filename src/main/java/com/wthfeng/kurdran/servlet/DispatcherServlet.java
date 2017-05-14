package com.wthfeng.kurdran.servlet;

import com.wthfeng.kurdran.render.Http404Renderer;
import com.wthfeng.kurdran.render.Http500Renderer;
import com.wthfeng.kurdran.render.Renderer;
import com.wthfeng.kurdran.servlet.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangtonghe
 * @date 2017/4/30 22:47
 */
public class DispatcherServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestHandler requestHandler;

    private ArgsHandler argsHandler;

    private MethodInvokeHandler methodHandler;

    private RenderHandler renderHandler;


    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.debug("servlet init start ...");

        initStrategies();
        System.out.println("servlet 开始");


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
        methodHandler = new MethodInvokeHandler();
        requestHandler = new RequestHandler();


    }


    private void doDispatch(ApplicationContent content) {

        RequestResult requestResult = new RequestResult();
        try {
            requestHandler.handle(content, requestResult);
            if (requestResult.getRequestMapping() != null) {
                argsHandler.handle(content,requestResult);
                methodHandler.handle(content,requestResult);
                renderHandler.handle(content,requestResult);


            }
        }catch (Exception e){
            content.setRenderer(new Http500Renderer(e));
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
