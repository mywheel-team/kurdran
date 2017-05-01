package com.wthfeng.kurdran.beans.factory;

import com.wthfeng.kurdran.beans.Bean;
import test.HelloAction;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:30
 */
public class BeanFactoryImpl implements BeanFactory {

    private Map<String,Bean> beans = new ConcurrentHashMap<>();


     {
        Class<HelloAction> clazz = HelloAction.class;
        Annotation[] annotations = clazz.getAnnotations();
        Bean bean = new Bean();
        bean.setAnnotations(annotations);
        String beanName = clazz.getSimpleName();
        beanName = beanName.substring(0,1).toLowerCase()+beanName.substring(1);
        bean.setName(beanName);
        bean.setScope(Singleton.class);
        bean.setBeanClass(clazz);
        beans.put(beanName,bean);
    }

    @Override
    public Bean getBean(String name) {
        return beans.get(name);
    }

    @Override
    public Bean getBean(Class requiredType) {
       for(Bean bean:beans.values()){
           System.out.println(bean.getBeanClass());
           if(requiredType.equals(bean.getBeanClass())){
               return bean;
           }
       }
       return null;
    }

    public void getActionAnnotation(Class<Annotation> annotationClass){


    }

    public static void main(String[] args) {
       BeanFactoryImpl factory = new BeanFactoryImpl();
        System.out.println(factory.getBean(HelloAction.class));
    }

}
