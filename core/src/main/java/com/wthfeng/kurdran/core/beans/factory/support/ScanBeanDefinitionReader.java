package com.wthfeng.kurdran.core.beans.factory.support;

import com.wthfeng.kurdran.core.beans.factory.BeanRegistry;

/**
 * @author wangtonghe
 * @since 2021/6/2 18:23
 */
public class ScanBeanDefinitionReader {

    private BeanRegistry beanRegistry;

    public ScanBeanDefinitionReader(BeanRegistry beanRegistry) {
        this.beanRegistry = beanRegistry;
    }

    public void scan(String... packages) {

    }
}
