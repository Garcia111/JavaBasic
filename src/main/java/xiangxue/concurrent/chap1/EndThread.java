package xiangxue.concurrent.chap1;

/**
 * @author：Cheng.
 * @date：Created in 14:46 2019/12/26
 */
public class EndThread {

    private static class UseThread extends Thread{

        public UseThread(String name){
            super(name);
        }


        @Override
        public void run(){
            String threadName = currentThread().getName();
            System.out.println("threadName:" + threadName +", isInterrupted:"+currentThread().isInterrupted());
//            while (!isInterrupted()){
                while (!interrupted()){
//                    while (true){
                        System.out.println(threadName+" is running");
                        System.out.println("isInterrupted:"+currentThread().isInterrupted());
                    }
            System.out.println("isInterrupted after while:"+currentThread().isInterrupted());
        }
    }



    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("endThread");
        endThread.start();
        Thread.sleep(20);
        endThread.interrupt();//中断线程，其实设置线程的标识位true
    }

}
