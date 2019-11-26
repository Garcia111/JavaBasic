package com.example.javabasic.thread.share_resource;

/**
 * 这里将共享资源与实际执行的任务进行分割，多个线程在执行任务的时候需要访问同一个共享资源
 */
public abstract class IntGenerator {

    //为了保持可见性，canceled标志位是用volatile修饰的
    private volatile  boolean canceled = false;

    public abstract  int next();

    //设置共享资源
    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        //canceled标志是boolean类型的，所以它是原子性的，即诸如赋值和返回值这样的简单操作
        //在发生时没有中断的可能。
        return canceled;
    }

}
