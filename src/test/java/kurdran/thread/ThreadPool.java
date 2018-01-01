package kurdran.thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangtonghe
 * @date 2017/12/9 23:06
 */
public class ThreadPool {


    private int DEFAULT_THREAD_NUM = 5;


    //线程空闲时间，单位毫秒
    private int DEFAULT_THREAD_KEEP_TIME = 5000;


    private AtomicInteger threadNumber = new AtomicInteger(0);

    private final LinkedList<Runnable> tasks = new LinkedList<>();


    private List<Worker> workers = new ArrayList<>();


    private ThreadPool(int threadNum) {
        initWorker(threadNum);
    }

    public void execute(Runnable task) {
        synchronized (tasks) {
            tasks.addLast(task);
            tasks.notify();
        }
    }

    public ThreadPool() {
        initWorker(DEFAULT_THREAD_NUM);
    }


    public int getActiveNum() {
        return 0;
    }


    public void shutdown() {

        workers.forEach(Worker::shutdown);

    }

    /**
     * 任务是否已全部完结
     * @return bool
     */
    public boolean isTaskOver(){
        return tasks.size()==0&&workers.size()==0;

    }


    private void initWorker(int threadNum) {
        for (int i = 0; i < threadNum; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "threadPool-" + threadNumber.incrementAndGet());
            thread.start();
        }
    }


    class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                synchronized (tasks) {
                    long start = System.currentTimeMillis();
                    long end = start+DEFAULT_THREAD_KEEP_TIME;
                    while (tasks.isEmpty()&&System.currentTimeMillis()<=end) {
                        try {
                            tasks.wait(DEFAULT_THREAD_KEEP_TIME);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("tasks:"+tasks.size());
                    System.out.println("works:"+workers.size());
                    if(tasks.isEmpty()){  //超时，停止线程
                        running = false;
                    }else{    //执行线程
                        Runnable task = tasks.removeFirst();
                        task.run();
                    }

                }
            }
            workers.remove(this);
        }

        private void shutdown() {
            running = false;

        }
    }
}
