package com.wthfeng.kurdran.core.sync;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * @author wangtonghe
 * @date 2017/12/10 16:49
 */
public class MyLock {

    private Sync sync = new Sync();

    //AQS的子类，由于是独占锁，实现tryAcquire和tryRelease两方法
    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            //若状态为1，说明有其他线程已占有锁，直接返回false
            if (getState() == arg) {
                return false;
            }
            //若状态为0，将其设为1，表示占有锁
            return compareAndSetState(0, arg);
        }

        @Override
        protected boolean tryRelease(int arg) {
            //设置状态为0，表示释放锁
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

        Condition newCondition() {
            return new ConditionObject();
        }
    }

    //加锁方法
    public void lock() {
        sync.acquire(1);

    }

    //解锁方法
    public void unlock() {
        sync.release(1);

    }

    public Condition getCondition(){
        return sync.newCondition();
    }
}
