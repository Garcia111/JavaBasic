package xiangxue.concurrent.chap1.base.safeend;

/**
 * 类说明：实现Runnable的线程如何安全的中断线程
 * @author：Cheng.
 * @date：Created in 14:42 2020/1/19
 */
public class EndRunnable {

    private static class UseRunnable implements Runnable{

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName()+
                        " I am implements Runnable. ");
            }
            System.out.println(Thread.currentThread().getName()+
                    " interrupt flag is "+Thread.currentThread().isInterrupted() );
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable = new UseRunnable();
        Thread thread = new Thread(useRunnable,"endThread");
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }
}


