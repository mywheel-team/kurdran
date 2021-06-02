package com.wthfeng.kurdran.core.beans.factory.support;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;

/**
 * 实例化策略
 *
 * @author wangtonghe
 * @since 2021/6/2 19:35
 */
public interface InstantiationStrategy {

    Object instantiation(BeanDefinition beanDefinition, String beanName);

}
