package com.wthfeng.kurdran.core.context;

/**
 * @author wangtonghe
 * @since 2021/6/2 16:28
 */
public interface AnnotationConfigRegistry {

    /**
     * 向容器注册配置类
     *
     * @param componentClasses
     */
    void register(Class<?>... componentClasses);


    void scan(String... packages);


}
