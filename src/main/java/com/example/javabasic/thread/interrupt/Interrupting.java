package com.example.javabasic.thread.interrupt;

/**
 * @author：Cheng.
 * @date：Created in 9:17 2019/12/5
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * sleep导致阻塞状态
 */
class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(100);
        }catch (InterruptedException e){
            System.out.println("Interrupted Exception");
        }
        System.out.println("Interrupted Exception");
    }
}

/**
 * IO阻塞
 */
class IOBlocked implements Runnable{

    private InputStream in;

    public IOBlocked(InputStream is){in = is;}

    @Override
    public void run() {
        try{
            System.out.println("waiting for read()");
            in.read();
        }catch (IOException e){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from blocked I/O");
            }else{
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable{

    public synchronized void f(){
        while (true){
            //never release lock ??
            Thread.yield();
        }
    }


    public SynchronizedBlocked(){
        new Thread(){
            @Override
            public void run(){
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        System.out.println("Trying to call f()");
        f();
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}


public class Interrupting {

    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        //使用submit执行某个任务，会返回一个Future对象，使用此对象可以中断该任务
        Future<?> f  =exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrpting "+r.getClass().getName());
        //调用future.cancel(true)中断该线程
        f.cancel(true);
        System.out.println("Interrupt sent to "+r.getClass().getName());
    }


    public static void main(String[] args) throws Exception{
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }

}
