package com.wthfeng.kurdran.core.context;

/**
 * 应用生命周期 接口
 *
 * @author wangtonghe
 * @since 2021/6/2 16:46
 */
public interface Lifecycle {

    void start();

    void end();

    boolean isRunning();
}
