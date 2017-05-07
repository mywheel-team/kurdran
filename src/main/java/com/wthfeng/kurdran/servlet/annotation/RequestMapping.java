package com.wthfeng.kurdran.servlet.annotation;

import com.wthfeng.kurdran.servlet.RequestMethod;

import java.lang.annotation.*;

/**
 * @author wangtonghe
 * @date 2017/5/1 17:33
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    /**
     * the request url
     */
    String[] value() default {};

    /**
     *the request method
     */
    RequestMethod[] method() default {};

}
