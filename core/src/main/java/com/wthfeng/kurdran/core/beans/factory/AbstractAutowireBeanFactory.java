package com.wthfeng.kurdran.core.beans.factory;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;
import com.wthfeng.kurdran.core.beans.factory.support.InstantiationStrategy;
import com.wthfeng.kurdran.core.beans.factory.support.JdkReflexStrategy;

/**
 * 自动装配bean 工厂
 *
 * @author wangtonghe
 * @since 2021/6/2 15:59
 */
public abstract class AbstractAutowireBeanFactory extends AbstractBeanFactory {


    private InstantiationStrategy instantiationStrategy = new JdkReflexStrategy();


    /**
     * 创建bean
     *
     * @return obj
     */
    Object createBean() {
        return new Object();
    }


    @Override
    Object createBean(String beanName, BeanDefinition beanDefinition) {
        return doCreateBean(beanName, beanDefinition);
    }

    /**
     * 真正开始创建bean了
     * 1. 使用反射创建bean实例
     * 2. 初始化属性
     * 3. 回调初始方法，用于实现aop
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {

        // todo 需要包装一下
        Object bean = getInstantiationStrategy().instantiation(beanDefinition, beanName);

        initProp(beanName, beanDefinition);

        initializeBean(beanName, beanDefinition);

        return bean;


    }

    private void initProp(String beanName, BeanDefinition beanDefinition) {




    }

    private void initializeBean(String beanName, BeanDefinition beanDefinition) {

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
