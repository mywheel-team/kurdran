package com.wthfeng.kurdran.ioc;

import com.wthfeng.kurdran.util.DynamicScanUtil;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:30
 */
public class BeanFactoryImpl implements BeanFactory {

    private Map<String, Bean<?>> beans = new ConcurrentHashMap<>();


    public static BeanFactory newInstance(Class<?> clazz) throws Exception {
        Map<String, Bean<?>> beans = DynamicScanUtil.scanByClassName(clazz);

        return new BeanFactoryImpl(beans);
    }


    private BeanFactoryImpl(Map<String, Bean<?>> beans) {
        this.beans = beans;

    }

    public BeanFactoryImpl() {

    }


    @Override
    public Bean getBean(String name) {
        return beans.get(name);
    }

    @Override
    public List<Bean<?>> getBeans(Class<? extends Annotation> annotation) {
        List<Bean<?>> list = new ArrayList<>();
        for (Bean<?> bean : beans.values()) {
            List<Class<? extends Annotation>> annotationList = bean.getAnnotations();
            if (annotationList.contains(annotation)) {
                list.add(bean);
            }
        }
        return list;
    }

    @Override
    public <T> Bean<T> getBean(Class<T> requiredType) {
        for (Bean bean : beans.values()) {
            if (requiredType.equals(bean.getBeanType())) {
                return bean;
            }
        }
        return null;
    }


}
