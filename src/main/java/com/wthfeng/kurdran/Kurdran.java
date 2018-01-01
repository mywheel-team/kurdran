package com.wthfeng.kurdran;

import com.wthfeng.kurdran.ioc.Bean;
import com.wthfeng.kurdran.ioc.BeanFactoryImpl;
import com.wthfeng.kurdran.mvc.annotation.Action;
import com.wthfeng.kurdran.mvc.annotation.RequestMapping;
import com.wthfeng.kurdran.mvc.handler.MethodInfo;
import com.wthfeng.kurdran.server.KurdranServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mvc服务启动类
 *
 * @author wangtonghe
 * @date 2017/12/2 12:23
 */
public class Kurdran {


    public int port;

    private final int DEFAULT_PORT = 9000;

    private Logger logger = LoggerFactory.getLogger(Kurdran.class);

    public List<MethodInfo> methodInfos = new ArrayList<>();


    public static Kurdran me() {
        return new Kurdran();
    }

    /**
     * 服务启动方法
     */
    public void start(Class<?> clazz, int port) {

        try {
            //bean的初始化
            initBean(clazz);
            this.port = getPort(port);
            KurdranServer server = new KurdranServer(Kurdran.this);
            server.start(clazz);
        } catch (Exception e) {
            logger.error("服务器启动错误", e);
        }

    }

    public void start(Class<?> clazz) {
        start(clazz, DEFAULT_PORT);
    }


    private void initBean(Class<?> clazz) throws Exception {
        List<Bean<?>> beans = BeanFactoryImpl.newInstance(clazz).getBeans(Action.class);
        convert2MethodBean(beans);

    }


    private int getPort(int port) throws Exception {
        if (port < 1024 || port > 65535) {
            throw new Exception("port error");
        } else {
            return port;
        }

    }


    private void convert2MethodBean(List<Bean<?>> beans) {
        beans.forEach(bean -> {
            Class<?> clazz = bean.getBeanType();
            Method[] methods = clazz.getMethods();
            Arrays.stream(methods).forEach(m -> {
                RequestMapping MthMappingAnnotation = m.getAnnotation(RequestMapping.class);
                if (MthMappingAnnotation != null) {
                    RequestMapping reqMappingAnnotation = clazz.getAnnotation(RequestMapping.class);
                    String prefixMapping = reqMappingAnnotation.value().length > 0 ? reqMappingAnnotation.value()[0] : "";
                    MethodInfo methodInfo = new MethodInfo();
                    methodInfo.setInvokeMethod(m);
                    methodInfo.setMethodType(MthMappingAnnotation.method());
                    List<String> mappingList = Arrays.stream(MthMappingAnnotation.value()).map(e ->
                            prefixMapping + e
                    ).collect(Collectors.toList());

                    String[] reqMappings = mappingList.toArray(new String[0]);
                    methodInfo.setRequestMapping(reqMappings);
                    methodInfos.add(methodInfo);
                }
            });
        });

    }
}
