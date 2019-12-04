package com.example.javabasic.thread.sync;

/**
 * @author：Cheng.
 * @date：Created in 9:05 2019/12/4
 */

class DualSynch{
    private Object syncObject = new Object();


    /**
     * 使用synchronized修饰方法，锁住的是当前类对象
     */
    public synchronized void f(){
        for(int i=0;i<5;i++){
            System.out.println("f()");
            Thread.yield();
        }
    }


    public void g(){
        synchronized (syncObject){
            for(int i=0;i<5;i++){
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}



public class SyncObject {

    public static void main(String[] args){
        final DualSynch ds = new DualSynch();
        new Thread(){
            @Override
            public void run(){
                ds.f();
            }
        }.start();
        ds.g();
    }
}
