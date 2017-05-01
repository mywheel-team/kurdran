package com.wthfeng.kurdran.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * kurdran default listener
 * @author wangtonghe
 * @date 2017/5/1 18:05
 */
public class WebContentListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void contextInitialized(ServletContextEvent sce) {
        logger.debug("servlet content start.......");
        //TODO 查找bean并注册


    }

    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("servlet content end.......");
        //TODO 销毁工作


    }
}
