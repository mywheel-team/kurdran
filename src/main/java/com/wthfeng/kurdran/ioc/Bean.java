package com.wthfeng.kurdran.ioc;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:02
 */
public interface Bean<T>  {

    String getName();

    Class<T> getBeanType();

    List<Class<? extends Annotation>> getAnnotations();

}
