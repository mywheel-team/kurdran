package kurdran.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangtonghe
 * @date 2017/12/14 21:54
 */
public class InnerTest {


    private Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    private int a;

    public  void hello(){
        new Inner();
    }

    public class Inner{
       Inner(){
           System.out.println(a);
           a=100;
        }
    }

    public static void main(String[] args) {


    }


    public void await()throws Exception{
        lock.lock();
        //操作
        condition.await();
        lock.unlock();
        condition.signal();

    }
}
