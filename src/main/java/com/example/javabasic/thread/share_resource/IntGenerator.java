package com.example.javabasic.thread.share_resource;

public abstract class IntGenerator {

    //为了保持可见性，canceled标志位是用volatile修饰的
    private volatile  boolean canceled = false;

    public abstract  int next();

    public void cancel(){
        canceled = true;
    }

    public boolean isCanceled(){
        //因为canceled标志是boolean类型的，所以它是原子性的，即诸如赋值和返回值这样的简单操作
        //在发生时没有中断的可能。
        return canceled;
    }

}
