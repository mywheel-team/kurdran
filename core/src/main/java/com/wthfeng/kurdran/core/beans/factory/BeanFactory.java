package com.wthfeng.kurdran.core.beans.factory;

/**
 * @author wangtonghe
 * @since 2021/6/2 15:54
 */
public interface BeanFactory {

    Object getBean(String beanName);

    Object getBean(Class<?> clazz);


}
