package xiangxue.concurrent.concurrent_in_action.chap3;

/**
 * @author：Cheng.
 * @date：Created in 16:31 2020/1/6
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{

        @Override
        public void run(){
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
        //程序有可能一直循环，有可能打印0，或42
        //程序有可能会打印0，因为早在对number赋值之前，主线程就已经写入ready并使之对读取线程可见，
        //这是一种重排序现象：在单个线程中，只要重排序不会对结果产生影响就不能保证其中的操作一定按照
        //程序写定的顺序执行，即使重排序对其他线程来说会产生明显影响。

    }

}
