package com.example.javabasic.thread.count_down_latch;

import org.junit.experimental.max.CouldNotReadCoreException;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 14:48 2019/12/13
 */

class TaskPortion implements Runnable{

    private static int counter = 0;

    private final int id = counter++;

    private static Random rand = new Random(47);

    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch){
        this.latch = latch;
    }


    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
            System.out.println("===count:"+latch.getCount());
        }catch (InterruptedException ex){
            System.out.println("TaskPortion interrupt exception");
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this+" completed");
    }

    @Override
    public String toString(){
        return String.format("%1$-3d ",id);
    }
}

/**
 * 等待CountDownLatch的任务
 */
class WaitingTask implements Runnable{

    private static int counter = 0;

    private final int id = counter++;

    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            //将会被挂起
            latch.await();
            System.out.println("Latch barrier passed for "+this);
        }catch (InterruptedException ex){
            System.out.println(this +" interrupted");
        }
    }

    @Override
    public String toString(){
        return String.format("WaitingTask %1$-3d ",id);
    }

}


public class CountDownLatchDemo {

    static final int SIZE = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        //CountDownLatch中的count值为100
        CountDownLatch latch = new CountDownLatch(SIZE);
        //10个线程将会被挂起，等待countDownLatch 减为0
        for (int i=0; i<10; i++){
            exec.execute(new WaitingTask(latch));
        }


        for (int i=0; i<SIZE; i++){
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("Launched all tasks");
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();

    }

}
