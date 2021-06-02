package com.wthfeng.kurdran.core.context;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;
import com.wthfeng.kurdran.core.beans.factory.BeanRegistry;
import com.wthfeng.kurdran.core.beans.factory.DefaultListableBeanFactory;
import com.wthfeng.kurdran.core.beans.factory.ListableBeanFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author wangtonghe
 * @since 2021/6/2 17:28
 */
public class GenericApplicationContext extends AbstractApplicationContext implements BeanRegistry {


    private AtomicBoolean refreshed;

    private final DefaultListableBeanFactory beanFactory;


    public GenericApplicationContext(DefaultListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    protected void refreshBeanFactory() {

        refreshed.compareAndSet(false, true);

    }


    @Override
    public ListableBeanFactory getBeanFactory() {
        return beanFactory;
    }


    @Override
    public void registry(String beanName, BeanDefinition beanDefinition) {

        beanFactory.registry(beanName, beanDefinition);

    }

    @Override
    public BeanDefinition removeRegistry(String beanName) {

        return beanFactory.removeRegistry(beanName);

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanFactory.getBeanDefinition(beanName);
    }
}
