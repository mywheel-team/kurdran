package com.wthfeng.kurdran.beans.factory;

import com.wthfeng.kurdran.beans.Bean;

/**
 * @author wangtonghe
 * @date 2017/5/1 19:07
 */
public interface BeanFactory {

    Bean getBean(String name);

    Bean getBean(Class requiredType);





}
