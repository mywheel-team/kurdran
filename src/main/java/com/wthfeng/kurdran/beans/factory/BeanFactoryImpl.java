package com.wthfeng.kurdran.beans.factory;

import com.wthfeng.kurdran.beans.Bean;
import com.wthfeng.kurdran.beans.BeanImpl;
import com.wthfeng.test.HelloAction;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:30
 */
public class BeanFactoryImpl implements BeanFactory {

    private Map<String, Bean<?>> beans = new ConcurrentHashMap<>();


    {
        /**
         * 模拟bean工厂
         */
        Class clazz = HelloAction.class;
        Annotation[] annotations = clazz.getAnnotations();
        List<Annotation> annotationList = Arrays.asList(annotations);
        String beanName = clazz.getSimpleName();
        beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
        Bean bean = new BeanImpl(beanName, Singleton.class, clazz, annotationList);
        beans.put(beanName,bean);

    }

    @Override
    public Bean getBean(String name) {
        return beans.get(name);
    }

    @Override
    public List<Bean<?>> getBeans(Class<? extends Annotation> annotation) {
        List<Bean<?>> list = new ArrayList<>();
        for (Bean<?> bean:beans.values()) {
            List<Class<? extends Annotation>> annotationList = bean.getAnnotations();
            if(annotationList.contains(annotation)){
                list.add(bean);
            }
        }
        return list;
    }

    @Override
    public <T>Bean<T> getBean(Class<T> requiredType) {
        for (Bean bean : beans.values()) {
            if (requiredType.equals(bean.getBeanType())) {
                return bean;
            }
        }
        return null;
    }



    public static void main(String[] args) {
        BeanFactory factory = new BeanFactoryImpl();

    }

}
