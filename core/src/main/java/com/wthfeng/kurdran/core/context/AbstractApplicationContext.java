package com.wthfeng.kurdran.core.context;

import com.wthfeng.kurdran.core.beans.factory.ListableBeanFactory;
import com.wthfeng.kurdran.core.exception.BeanException;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangtonghe
 * @since 2021/6/2 16:14
 */
public abstract class AbstractApplicationContext implements ConfigurableApplicationContext {


    private long startTime;

    private final Object contextRefreshMonitor = new Object();

    private final AtomicBoolean active = new AtomicBoolean(false);

    private final AtomicBoolean closed = new AtomicBoolean(false);


    /**
     * 刷新应用上下文，最重要的方法
     * 模板方法模式
     */
    @Override
    public void refresh() throws BeanException, IllegalStateException {


        synchronized (contextRefreshMonitor) {


            // 刷新上下文前的准备
            prepareRefresh();

            // 构建bean工厂
            ListableBeanFactory beanFactory = buildBeanFactory();

            // beanfactory post 回调
            invokeBeanFactoryPostProcessor(beanFactory);

            // 完成bean初始化
            finishBeanFactoryInit(beanFactory);

        }

    }

    /**
     * 上下文刷新的预处理方法
     */
    private void prepareRefresh() {

        startTime = System.currentTimeMillis();
        active.set(true);
        closed.set(false);

    }

    private void invokeBeanFactoryPostProcessor(ListableBeanFactory beanFactory) {

        // todo


    }

    /**
     * 刷新bean工厂
     */
    protected abstract void refreshBeanFactory();

    /**
     * bean的初始化方法
     *
     * @param beanFactory
     */
    private void finishBeanFactoryInit(ListableBeanFactory beanFactory) {

        beanFactory.preInstantiateSingletons();

    }

    /**
     * 构建bean工厂
     */
    private ListableBeanFactory buildBeanFactory() {

        refreshBeanFactory();

        return getBeanFactory();

    }

    @Override
    public void setClassLoader() {

    }

    @Override
    public void registerShutdownHook() {

    }


    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void end() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }


}
