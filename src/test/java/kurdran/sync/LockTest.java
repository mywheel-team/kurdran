package kurdran.sync;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangtonghe
 * @date 2017/12/10 19:44
 */
public class LockTest {

    private static int total;
    static int num = 1000000;
    static CountDownLatch latch = new CountDownLatch(100);
    static MyLock myLock = new MyLock();

//    static Lock myLock = new ReentrantLock();

    private final static Object obj = new Object();


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            new Thread(new A()).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println(total);
    }

    static class A implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                myLock.lock();
                try {
                    total++;
                } finally {
                    myLock.unlock();

                }
            }
            latch.countDown();
        }
    }

    static class Simple implements Runnable {

        @Override
        public void run() {
            myLock.lock();
            try {
                total++;
            } finally {
                myLock.unlock();

            }
        }
    }

}
