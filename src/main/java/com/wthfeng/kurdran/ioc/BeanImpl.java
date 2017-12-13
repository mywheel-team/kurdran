package com.wthfeng.kurdran.ioc;

import javax.inject.Singleton;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/4 21:17
 */
public class BeanImpl<T> implements Bean<T> {


    /**
     * bean 名称
     */
    private String name;

    /**
     * bean 作用域
     */
    private Class<? extends Annotation> scope;

    /**
     * bean 类型
     */
    private Class<T> beanType;

    private List<Class<? extends Annotation>> annotations;

    public BeanImpl(String name, Class<? extends Annotation> scope, Class<T> beanType) {
        this.name = name;
        this.scope = scope;
        this.beanType = beanType;
        this.annotations = getAnnotation(beanType);
    }

    public BeanImpl(String name,  Class<T> beanType){
        this.name = name;
        this.beanType = beanType;
        this.annotations = getAnnotation(beanType);
        this.scope = Singleton.class;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Class<T> getBeanType() {
        return beanType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends Annotation> getScope() {
        return scope;
    }

    public void setScope(Class<? extends Annotation> scope) {
        this.scope = scope;
    }

    public void setBeanType(Class<T> beanType) {
        this.beanType = beanType;
    }

    @Override
    public  List<Class<? extends Annotation>> getAnnotations() {

        return annotations;
    }

    private List<Class<? extends Annotation>> getAnnotation(Class<T> beanType){
        Annotation[] annotations = beanType.getAnnotations();
        List<Class<? extends Annotation>> annotationList = new ArrayList<>();
        Arrays.stream(annotations).forEach(e->annotationList.add(e.annotationType()));
        return annotationList;

    }


}
