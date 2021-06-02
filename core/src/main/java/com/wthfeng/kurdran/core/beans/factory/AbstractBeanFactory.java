package com.wthfeng.kurdran.core.beans.factory;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;
import com.wthfeng.kurdran.core.beans.factory.support.InstantiationStrategy;
import com.wthfeng.kurdran.core.beans.singleton.DefaultSingletonBeanRegistry;
import com.wthfeng.kurdran.core.exception.BeanScopeNotSupportException;

/**
 * @author wangtonghe
 * @since 2021/6/2 15:58
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanRegistry {



    @Override
    public Object getBean(String beanName) {
        return doGetBean(beanName);
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return null;
    }

    private Object doGetBean(String beanName) {


        // 先从单例中获取
        Object bean = getSingleton(beanName);

        // 没有的话走创建逻辑
        if (bean == null) {

            BeanDefinition beanDefinition = getBeanDefinition(beanName);

            if (beanDefinition.isSingleton()) {


                bean = getSingleton(beanName, () ->

                        createBean(beanName, beanDefinition)

                );

            } else if (beanDefinition.isPrototype()) {

                bean = createBean(beanName, beanDefinition);

            } else {
                throw new BeanScopeNotSupportException("bean未知作用域");
            }

        }

        return bean;


    }


    abstract Object createBean(String beanName, BeanDefinition beanDefinition);


}
