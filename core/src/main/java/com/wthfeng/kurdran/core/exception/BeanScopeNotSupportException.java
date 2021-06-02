package com.wthfeng.kurdran.core.exception;

/**
 * @author wangtonghe
 * @since 2021/6/2 19:09
 */
public class BeanScopeNotSupportException extends BeanException {

    private String message;

    public BeanScopeNotSupportException(String message) {
        this.message = message;
    }
}
