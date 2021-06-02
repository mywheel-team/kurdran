package com.wthfeng.kurdran.core.beans.factory;

import com.wthfeng.kurdran.core.exception.BeanException;

/**
 * @author wangtonghe
 * @since 2021/6/2 17:11
 */
public interface ListableBeanFactory extends BeanFactory {

    String[] getBeanDefinitionNames();


    /**
     * 初始化剩余单例bean
     *
     * @throws BeanException
     */
    void preInstantiateSingletons() throws BeanException;


}
