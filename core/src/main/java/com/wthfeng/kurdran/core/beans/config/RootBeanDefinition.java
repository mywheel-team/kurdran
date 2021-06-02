package com.wthfeng.kurdran.core.beans.config;

/**
 * @author wangtonghe
 * @since 2021/6/2 15:49
 */
public class RootBeanDefinition implements BeanDefinition {


    private String beanClassName;

    private Class<?> beanClass;

    private boolean primary;

    private String initMethodName;

    private String destroyName;

    @Override
    public Class<?> getBeanClass() {
        return beanClass;
    }

    @Override
    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public RootBeanDefinition(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return beanClassName;
    }

    @Override
    public boolean getPrimary() {
        return primary;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @Override
    public void setInitMethodName(String methodName) {

        this.initMethodName = methodName;

    }

    @Override
    public void setDestroyMethodName(String methodName) {

        this.destroyName = methodName;

    }

    @Override
    public String getInitMethodName() {
        return initMethodName;
    }

    @Override
    public String getDestroyMethodName() {

        return destroyName;
    }
}
