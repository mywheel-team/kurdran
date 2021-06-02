package com.wthfeng.kurdran.core.beans.factory.support;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;
import com.wthfeng.kurdran.core.beans.config.RootBeanDefinition;
import com.wthfeng.kurdran.core.beans.factory.BeanRegistry;

/**
 * @author wangtonghe
 * @since 2021/6/2 18:07
 */
public class AnnotationBeanDefinitionReader {

    private BeanRegistry beanRegistry;

    public AnnotationBeanDefinitionReader(BeanRegistry beanRegistry) {
        this.beanRegistry = beanRegistry;
    }

    public void register(Class<?>... componentClasses) {

        // todo

        for (Class<?> clazz : componentClasses) {

            String className = clazz.getName();

            String beanName = className.toLowerCase();

            BeanDefinition beanDefinition = new RootBeanDefinition(className);

            beanRegistry.registry(beanName, beanDefinition);

        }

    }

}
