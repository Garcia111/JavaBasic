package com.example.javabasic.thread.notify_vs_notifyall;


import org.apache.commons.collections.set.SynchronizedSet;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker{

    synchronized  void waitingCall(){
        try{
            while(!Thread.interrupted()){
                //这里有两种离开循环的方式，一种是执行任务的线程被阻塞之后，一种是正在阻塞的线程被中断了
                wait();
                System.out.println(Thread.currentThread()+" ");
            }
        }catch (InterruptedException e){

        }
    }

    //执行wait() notify() notifyAll()方法需要首先获得操作对象的锁，因此需要加上synchronized
    synchronized  void prod(){notify();}
    synchronized  void prodAll(){notifyAll();}
}

class Task implements Runnable{

    static Blocker blocker = new Blocker();


    @Override
    public void run() {
        blocker.waitingCall();
    }
}


class Task2 implements Runnable{

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
       blocker.waitingCall();
    }
}




public class NotifyVsNotifyAll {


    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            //前5个线程在Task.blocker上阻塞
            exec.execute(new Task());
        }
        //在Task2.blocker上阻塞
        exec.execute(new Task2());



        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.println("\nnotify()");
                    //操作的是Task类的blocker对象，因此只会释放Task类的blocker对象的锁
                    //同时唤醒等待blocker对象锁的线程

                    //而执行Task2任务的线程不会被唤醒
                    Task.blocker.prod();
                    prod = false;
                }else{
                    System.out.println("\nnotifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        },400,400);


        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("Task2.blocker.prodAll()");
        //Timer已经cancel掉了，对应的Timer中开启的定时任务的线程就不会执行了，
        // 因此原来被唤醒的那5个线程将会处于阻塞状态，不会再被唤醒

        //手动唤醒等待执行Task2任务的线程
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdown();
    }





















}
