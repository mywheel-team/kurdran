package com.wthfeng.kurdran.mvc.annotation;

import java.lang.annotation.*;

/**
 * @author wangtonghe
 * @date 2017/5/1 17:17
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
    String value() default "";

}
