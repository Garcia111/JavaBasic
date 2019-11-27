package com.example.javabasic.thread.share_resource;

/**
 * @author：Cheng.
 * @date：Created in 10:32 2019/11/27
 */
public class SynchronizedEventGenerator extends IntGenerator {

    private int currentEventValue = 0;

    @Override
    public synchronized int next() {
        ++currentEventValue;
        Thread.yield();
        ++currentEventValue;
        return currentEventValue;
    }


    public static void main(String[] args){
        EventChecker.test(new SynchronizedEventGenerator());
    }
}
