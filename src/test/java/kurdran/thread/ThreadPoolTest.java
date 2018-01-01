package kurdran.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/12/16 13:25
 */
public class ThreadPoolTest {


    @Test
    public void testPool() {
        ThreadPool pool = new ThreadPool();
        Object obj = new Object();

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "：start");
                System.out.println(Thread.currentThread().getThreadGroup().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        synchronized (obj) {
            while (!pool.isTaskOver()) {
                try {
                    obj.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(System.currentTimeMillis() - start);
            System.out.println("线程池销毁");
        }
    }


    @Test
    public void testPool2() {
        AtomicInteger number = new AtomicInteger(0);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<AtomicInteger> result = executorService.submit(() -> {
            for (int i = 0; i < 1000; i++) {
                number.incrementAndGet();
            }

            System.out.println("开始执行:" + Thread.currentThread().getName());
            return number;
        });
        try {
            System.out.println(result.get().get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());

            });
        }
        try {
            executorService.awaitTermination(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test3() {
        Lock lock = new ReentrantLock();
        lock.unlock();
        System.out.println("解锁");
    }


}
