package com.wthfeng.kurdran.core.exception;

/**
 * kurdran异常基类
 *
 * @author wangtonghe
 * @since 2021/6/2 16:36
 */
public class KurdranBaseException extends RuntimeException {

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }


}
