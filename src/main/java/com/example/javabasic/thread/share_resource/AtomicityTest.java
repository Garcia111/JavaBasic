package com.example.javabasic.thread.share_resource;

import javax.sound.midi.SysexMessage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 16:23 2019/11/27
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    public int getValue(){
        return i;
    }

    public synchronized void evenIncrement(){i++;Thread.yield();i++;}

    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }


    public static void main(String[] args){
        ExecutorService exec = new ThreadPoolExecutor(5,30,
                60L, TimeUnit.SECONDS,new SynchronousQueue<>());
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while(true){
            int val = at.getValue();
            if(val % 2 != 0){
                System.out.println(val);
                System.exit(0);
            }
        }

    }
}
