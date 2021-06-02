package com.wthfeng.kurdran.core.context;


import com.wthfeng.kurdran.core.beans.factory.BeanFactory;

/**
 * @author wangtonghe
 * @date 2017/4/30 23:37
 */
public interface ApplicationContext extends BeanFactory {

    String getApplicationName();


}
