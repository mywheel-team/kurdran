package com.wthfeng.kurdran.core.beans.config;

/**
 * bean 定义顶层接口
 *
 * @author wangtonghe
 * @since 2021/6/2 15:38
 */
public interface BeanDefinition {

    /**
     * bean的class name
     *
     * @return bean class for name
     */
    String getBeanClassName();

    Class<?> getBeanClass();

    void setBeanClass(Class<?> beanClass);


    /**
     * 是否是主bean
     *
     * @return string
     */
    boolean getPrimary();

    /**
     * 默认单例
     *
     * @return 是否是单例
     */
    default boolean isSingleton() {
        return true;
    }

    boolean isPrototype();

    void setInitMethodName(String methodName);


    void setDestroyMethodName(String methodName);

    /**
     * 获取初始化方法
     *
     * @return string
     */
    String getInitMethodName();

    /**
     * 获取销毁时方法
     *
     * @return string
     */
    String getDestroyMethodName();


}
