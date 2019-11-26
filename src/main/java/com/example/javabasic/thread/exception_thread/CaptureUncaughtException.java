package com.example.javabasic.thread.exception_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


/**
 * 此任务在执行的时候，会打印当前线程的异常捕获类，并抛出异常
 */
 class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by "+t);
        System.out.println("eh = "+t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

/**
 * 异常捕获类，实现Thread.UncaughtExceptionHandler接口，覆盖其中的uncaughtExceptin()方法
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{


    /**
     * uncaughtException()方法捕获线程中逃逸的异常
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught "+e);
    }
}

/**
 * 线程工厂，实现ThreadFacotry接口，在创建线程的时候，指定该线程的异常捕获类
 */
class HandlerThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this+" creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created "+t);
        //设置一个线程的异常捕获类
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh="+t.getUncaughtExceptionHandler());
        return t;
    }
}


public class CaptureUncaughtException{

     public static void main(String[] args){
         /**
          * 使用线程池和线程工厂创建线程，创建出的线程每个都有一个异常捕获类MyUncaughtExceptinHandler类
          */
         ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
         exec.execute(new ExceptionThread2());
     }

}
