package com.example.javabasic.thread.ReentrantLock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex{

    private Lock lock = new ReentrantLock();

    public BlockedMutex(){
        //获取锁，并且从不释放锁
        lock.lock();
    }

    public void f(){
        try {
            //lockInterruptibly：获取锁定，除非当前线程是interrupted,如果锁可用则立即返回
            //如果锁不可用，则当前线程将被禁用以进行线程调度，并且处于休眠状态
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        }catch (InterruptedException e){
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }

}


class Blocked2 implements Runnable{

    BlockedMutex blocked = new BlockedMutex();


    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        //无法获取到锁，会被阻塞
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}




public class Interrupting2 {

    //733
    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt");
        //线程的中断，可以导致f()中捕获InterruptedException,在ReentrantLock上阻塞的任务具备可以被中断的能力
        t.interrupt();
    }

}











