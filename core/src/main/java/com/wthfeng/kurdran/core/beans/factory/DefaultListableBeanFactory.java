package com.wthfeng.kurdran.core.beans.factory;

import com.wthfeng.kurdran.core.beans.config.BeanDefinition;
import com.wthfeng.kurdran.core.exception.BeanException;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangtonghe
 * @since 2021/6/2 17:00
 */
public class DefaultListableBeanFactory extends AbstractAutowireBeanFactory implements ListableBeanFactory {


    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);


    @Override
    public String[] getBeanDefinitionNames() {

        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void preInstantiateSingletons() throws BeanException {

        Set<String> beanNames = beanDefinitionMap.keySet();

        for (String beanName : beanNames) {

            getBean(beanName);

        }


    }

    @Override
    public void registry(String beanName, BeanDefinition beanDefinition) {

        beanDefinitionMap.put(beanName, beanDefinition);

    }

    @Override
    public BeanDefinition removeRegistry(String beanName) {

        return beanDefinitionMap.remove(beanName);

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }
}
