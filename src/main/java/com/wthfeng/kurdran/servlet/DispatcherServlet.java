package com.wthfeng.kurdran.servlet;

import com.wthfeng.kurdran.servlet.handler.RequestHandler;
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
public class DispatcherServlet extends HttpServlet{

    private Logger logger = LoggerFactory.getLogger(getClass());


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

    private void initStrategies(){
        initRequestHandler();

    }

    private void initRequestHandler(){
        new RequestHandler();

    }

    private void doDispatch(ApplicationContent content){


    }


}
