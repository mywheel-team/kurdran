package com.wthfeng.kurdran.ioc;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:07
 */
public interface BeanFactory {

    /**
     * get bean by bean name
     * @param name bean name
     * @return bean
     */
    Bean<?> getBean(String name);

    /**
     * get bean by bean type
     * @param requiredType bean type name
     * @param <T>  bean type
     * @return bean
     */
    <T>Bean<T> getBean(final Class<T> requiredType);

    /**
     * 根据注解获取拥有该注解的类
     * @param annotation 注解类型
     * @return bean集合
     */
    List<Bean<?>> getBeans(final Class<? extends Annotation> annotation);
}
