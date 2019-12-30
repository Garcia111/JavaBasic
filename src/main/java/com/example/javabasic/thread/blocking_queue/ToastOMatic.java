package com.example.javabasic.thread.blocking_queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

class Toast{

    public enum Status{DRY,BUTTERED,JAMMED}

    private Status status = Status.DRY;

    private final int id;

    public Toast(int idn){
        id = idn;
    }


    public void butter(){status = Status.BUTTERED;}

    public void jam(){status = Status.JAMMED;}

    public Status getStatus(){return status;}

    public int getId(){return id;}

    public String toString(){
        return "Toast "+id+": "+status;
    }
}


class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable{

    private ToastQueue toastQueue;

    private int count = 0;

    private Random rand = new Random(47);

    public Toaster(ToastQueue tq){toastQueue = tq;}

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                //吐司机吐出一块面包，放入toastQueue中
                toastQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

/**
 * 向面包上抹黄油
 */
class Butterer implements Runnable{

    private ToastQueue dryQueue, butteredQueue;

    public Butterer(ToastQueue dry, ToastQueue buttered){
        dryQueue = dry;
        butteredQueue = buttered;
    }


    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                //将Toast抹上黄油之后，将其加入butteredQueue
                butteredQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}


/**
 * 给抹了黄油的面包继续抹上果酱
 */
class Jammer implements Runnable{
    private ToastQueue buttererdQueue, finishedQueue;

    public Jammer(ToastQueue buttered, ToastQueue finished){
        buttererdQueue = buttered;
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t = buttererdQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Jammer interrupted");
        }
    }
}


class Eater implements Runnable{

    private ToastQueue finishedQueue;

    private int counter = 0;

    public Eater(ToastQueue finished){
        finishedQueue = finished;
    }


    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t = finishedQueue.take();
                if(t.getId() != counter++ ||
                        t.getStatus() != Toast.Status.JAMMED){
                    System.out.println(">>>> Error: "+t);
                    System.exit(1);
                }else{
                    System.out.println("Chomp! "+t);
                }

            }
        }catch (InterruptedException e){
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}


public class ToastOMatic {

    //此实例中没有任何显式地同步，因为同步由队列和系统的设计隐式的管理了，每片Toast在任何时刻都只有一个任务在操作
    //因为队列的阻塞，使得处理福成将会被自动的挂起和恢复

    public static void main(String[] args) throws Exception{
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();

    }

}
