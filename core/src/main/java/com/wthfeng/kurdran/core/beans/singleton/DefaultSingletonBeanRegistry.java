package com.wthfeng.kurdran.core.beans.singleton;

import com.wthfeng.kurdran.core.beans.factory.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例bean 注册器
 *
 * @author wangtonghe
 * @since 2021/6/2 16:20
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    private final Map<String, Object> singletonObjectMap = new ConcurrentHashMap<>();

    private final Map<String, Object> earlyObjectMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {

        return getSingletonByCache(beanName);


    }

    public Object getSingleton(String beanName, ObjectFactory<?> factory) {

        Object bean = getSingletonByCache(beanName);

        if (bean != null) {
            return bean;
        }

        bean = factory.getObject();

        singletonObjectMap.put(beanName, bean);

        return bean;

    }

    public Object getSingletonByCache(String beanName) {

        Object bean = null;


        if ((bean = singletonObjectMap.get(beanName)) == null) {

            if ((bean = earlyObjectMap.get(beanName)) != null) {

                return bean;

            }

        }

        return bean;
    }


}
