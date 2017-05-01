package com.wthfeng.kurdran.beans;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:02
 */
public class Bean<T>  {

    /**
     * Bean name.
     */
    private String name;

    /**
     * Bean scope.
     */
    private Class<? extends Annotation> scope;

    /**
     * Bean qualifiers.
     */
    private Set<Annotation> qualifiers;

    /**
     * Bean class.
     */
    private Class<T> beanClass;

    private Annotation[] annotations;



    public String getName() {
        return name;
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

    public Set<Annotation> getQualifiers() {
        return qualifiers;
    }

    public void setQualifiers(Set<Annotation> qualifiers) {
        this.qualifiers = qualifiers;
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Annotation[] annotations) {
        this.annotations = annotations;
    }
}
