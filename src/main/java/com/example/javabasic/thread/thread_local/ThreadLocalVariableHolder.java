package com.example.javabasic.thread.thread_local;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author：Cheng.
 * @date：Created in 9:27 2019/12/4
 */

class Accessor implements Runnable{

    private final int id;

    public Accessor(int idn){id = idn;}

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            //每个线程都会维护自己的本地线程变量，进行本地线程变量自增
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString(){
        return "#"+id+": "+ThreadLocalVariableHolder.get();
    }
}


public class ThreadLocalVariableHolder {

    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);
        @Override
        protected synchronized Integer initialValue(){
            return rand.nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get()+1);
    }


    public static int get(){
        return value.get();
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = new ThreadPoolExecutor(0, 30,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        for(int i=0;i<5;i++){
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();

    }
}
