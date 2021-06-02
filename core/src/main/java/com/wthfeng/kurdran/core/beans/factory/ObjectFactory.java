package com.wthfeng.kurdran.core.beans.factory;

/**
 * @author wangtonghe
 * @since 2021/6/2 19:13
 */
@FunctionalInterface
public interface ObjectFactory<T> {

    T getObject();

}
