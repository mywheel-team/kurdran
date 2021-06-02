package com.wthfeng.kurdran.core.thread;

import com.wthfeng.kurdran.core.sync.MyLock;

import java.util.concurrent.locks.Condition;

/**
 * @author wangtonghe
 * @date 2017/12/14 21:59
 */
public class MyLockTest {

    static MyLock myLock = new MyLock();
    static Condition condition = myLock.getCondition();
    static int num;
    static volatile boolean flag = false;

    public static void main(String[] args) throws Exception {

        Thread a = new Thread(() -> {
            while (true) {
                if (flag) {
                    try {
                        myLock.lock();
                        flag = false;
                        Thread.sleep(1000);
                        System.out.println("num:" + num);
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();

                    } finally {
                        myLock.unlock();
                    }
                }
            }
        });

        Thread b = new Thread(() -> {
            for (; ; num++) {
                if (num % 1000000 == 0) {
                    myLock.lock();
                    try {
                        flag = true;
                        condition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        myLock.unlock();

                    }
                }
            }

        });
        b.start();
        a.start();
        a.join();
        b.join();

    }


}
