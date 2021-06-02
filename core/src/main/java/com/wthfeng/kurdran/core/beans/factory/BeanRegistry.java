package com.wthfeng.kurdran.core.beans.factory;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;

/**
 * @author wangtonghe
 * @since 2021/6/2 15:57
 */
public interface BeanRegistry {

    void registry(String beanName, BeanDefinition beanDefinition);

    BeanDefinition removeRegistry(String beanName);

    BeanDefinition getBeanDefinition(String beanName);


}
