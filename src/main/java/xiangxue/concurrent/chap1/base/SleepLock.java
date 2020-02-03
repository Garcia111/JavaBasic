package xiangxue.concurrent.chap1.base;

/** 类说明：测试sleep对锁的影响，线程sleep不会释放锁
 * @author：Cheng.
 * @date：Created in 16:06 2020/1/19
 */
public class SleepLock {

    private Object lock = new Object();

    private class ThreadSleep extends Thread{

        @Override
        public void run(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock ");
            try{
                synchronized (lock){
                    System.out.println(threadName+" taking the lock");
                    Thread.sleep(5000);
                    System.out.println("Finish the work: "+threadName);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    private class ThreadNotSleep extends Thread{

        @Override
        public void run(){
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock time="+System.currentTimeMillis());
            synchronized (lock){
                System.out.println(threadName+" taking the lock time="+System.currentTimeMillis());
                System.out.println("Finish the work " + threadName);
            }
        }
    }


    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try{
            Thread.sleep(1000);
            System.out.println(" Main slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }



}
