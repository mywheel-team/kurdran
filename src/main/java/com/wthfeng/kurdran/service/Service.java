package com.wthfeng.kurdran.service;

import javax.inject.Named;
import java.lang.annotation.*;

/**
 * @author wangtonghe
 * @date 2017/5/4 21:54
 */
@Named
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Service {

    String value() default "";

}
