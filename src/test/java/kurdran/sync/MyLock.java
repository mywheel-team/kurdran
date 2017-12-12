package kurdran.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wangtonghe
 * @date 2017/12/10 16:49
 */
public class MyLock implements Lock {

    private Sync sync = new Sync();

    //AQS的子类，由于是独占锁，实现tryAcquire和tryRelease两方法
    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            //若状态为1，说明有其他线程已占有锁，直接返回false
            if(getState()==arg){
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
    }

    //其他Lock接口方法，直接调用Sync类实现

    @Override
    public void lock() {
        sync.acquire(1);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }


    @Override
    public void unlock() {
        sync.release(1);

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
