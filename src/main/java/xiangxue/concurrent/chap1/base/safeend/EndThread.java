package xiangxue.concurrent.chap1.base.safeend;

/**
 * 继承Thread类的线程如何安全中断线程，静态方法interrupted()在判断标志位之后，会将标志位clear
 * 类实例方法isInterrupted()，只是判断标志位
 *
 * interrupt()只会设置一下标志位并不会立即终止线程，需要手动判断一下标志位终止线程，这给了程序员
 * 释放资源的时间
 *
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
