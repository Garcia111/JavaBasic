package com.example.javabasic.thread.share_resource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Cheng.
 * @date：Created in 10:48 2019/11/27
 */
public class MutexEventGenerator extends IntGenerator {

    private int currentEventValue;

    private Lock lock = new ReentrantLock();


    @Override
    public int next() {
        lock.lock();
        try{
           ++currentEventValue;
           Thread.yield();
           ++currentEventValue;
           //注意 return语句必须在try子句中出现，以确保unlock()不会过早发生，从而将数据暴露给第二个任务
           return currentEventValue;
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args){
        EventChecker.test(new MutexEventGenerator());
    }

}
