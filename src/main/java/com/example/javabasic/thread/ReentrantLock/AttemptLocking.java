package com.example.javabasic.thread.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Cheng.
 * @date：Created in 10:58 2019/11/27
 */
public class AttemptLocking {

    private ReentrantLock lock = new ReentrantLock();

    /**
     * 尝试获得锁，并打印获得锁的结果
     */
    public void untimed(){
        boolean captured = lock.tryLock();
        try{
            System.out.println("tryLock:"+captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }


    /**
     * 尝试获得锁，持续时间为两秒钟，然后打印是否获得了锁
     */
    public void timed(){
        boolean captured = false;
        try{
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }


        try{
            System.out.println("tryLock(2,TimeUnit.SECONDS):"+captured);
        }finally {
            if(captured){
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al =  new AttemptLocking();
        //主线程尝试获得锁
        al.untimed();
        al.timed();

        new Thread(){
            //静态代码块设置此线程为守护线程
            {setDaemon(true);}

            @Override
            public void run(){
                //新的线程获得了锁，而且一直没有释放
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        //主线程让步，让新建线程执行，此时锁被新建的线程拿到
        Thread.yield();
        Thread.sleep(1000);
        //主线程再向尝试获得锁，则会失败
        al.untimed();
        al.untimed();
    }

}
