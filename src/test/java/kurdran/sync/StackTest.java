package kurdran.sync;

/**
 * @author wangtonghe
 * @date 2017/12/11 10:35
 */
public class StackTest {

    private static int i;

    public static void main(String[] args) {
       Thread a =  new Thread(new Task());
       a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    static class Task implements Runnable{

        @Override
        public void run() {
            while (true){
                i++;
            }
        }
    }
}
