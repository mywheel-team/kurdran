package com.wthfeng.kurdran.core.context;

import com.wthfeng.kurdran.core.beans.factory.ListableBeanFactory;

/**
 * 可配置的应用上下文
 *
 * @author wangtonghe
 * @since 2021/6/2 16:43
 */
public interface ConfigurableApplicationContext extends ApplicationContext, Lifecycle {


    void refresh();


    void setClassLoader();


    void registerShutdownHook();

    ListableBeanFactory getBeanFactory();

    @Override
    default Object getBean(String beanName) {
        return null;
    }

    @Override
    default Object getBean(Class<?> clazz) {
        return null;
    }
}
