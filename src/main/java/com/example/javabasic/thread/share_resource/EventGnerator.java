package com.example.javabasic.thread.share_resource;

public class EventGnerator extends IntGenerator {

    private int currentEventValue =0;

    @Override
    public int next() {
        //因为next方法并不是同步代码块，两次连续递增操作之间会有线程安全问题
        //在Java中，递增不是原子性的操作，因此，即使单一的递增也不是线程安全的。

        ++currentEventValue;
        Thread.currentThread().yield();
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args){
        EventChecker.test(new EventGnerator());
    }

}
