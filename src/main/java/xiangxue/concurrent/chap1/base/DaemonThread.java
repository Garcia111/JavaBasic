package xiangxue.concurrent.chap1.base;

/**
 * @author：Cheng.
 * @date：Created in 15:05 2020/1/19
 */
public class DaemonThread {

    private static class UseThread extends Thread{

        @Override
        public void run(){
            try{
                while (!isInterrupted()){
                    System.out.println(Thread.currentThread().getName()+
                            " I am extends Thread. ");
                }
                System.out.println(Thread.currentThread().getName()+"interrupt flag is "+isInterrupted());
            }finally {
                //守护线程中的finally不一定起作用，有可能执行有可能不执行
                System.out.println(".......finally");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        Thread.sleep(5);
        useThread.interrupt();
    }
}













