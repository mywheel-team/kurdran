package com.wthfeng.kurdran.core.beans.factory.support;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author wangtonghe
 * @since 2021/6/2 19:36
 */
public class JdkReflexStrategy implements InstantiationStrategy {

    @Override
    public Object instantiation(BeanDefinition beanDefinition, String beanName) {

        String beanClassName = beanDefinition.getBeanClassName();


        Object bean = null;


        try {
            Class<?> clazz = Class.forName(beanClassName);

            beanDefinition.setBeanClass(clazz);

            Constructor<?> constructorToUse = clazz.getDeclaredConstructor();

            constructorToUse.setAccessible(true);

            bean = constructorToUse.newInstance();

        } catch (Exception e) {
            //
        }

        return bean;
    }
}
