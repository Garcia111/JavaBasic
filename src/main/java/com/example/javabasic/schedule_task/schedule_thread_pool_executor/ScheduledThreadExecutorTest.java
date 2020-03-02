package com.example.javabasic.schedule_task.schedule_thread_pool_executor;

import java.util.Date;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author：Cheng.
 * @date：Created in 16:16 2019/12/2
 */
public class ScheduledThreadExecutorTest {

    private ScheduledExecutorService scheduledExecutorService;
    private long start;


    public ScheduledThreadExecutorTest() {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(2);
        start = System.currentTimeMillis();
    }


    public static void main(String[] args) {
        ScheduledThreadExecutorTest testScheduledExecutorTimer = new ScheduledThreadExecutorTest();
//        System.out.println("任务开始: " + testScheduledExecutorTimer.start);
        testScheduledExecutorTimer.timerOne();
        testScheduledExecutorTimer.timerTwo();
        testScheduledExecutorTimer.timerThree();
        testScheduledExecutorTimer.timerFour();
    }


    public void timerOne(){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
//                System.out.println("任务开始执行,与开始时间的时间间隔为: " +(System.currentTimeMillis() - start) + "毫秒");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000, TimeUnit.MILLISECONDS);
    }

    public void timerTwo(){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
//                System.out.println("任务2开始执行: " + (System.currentTimeMillis()-start) + "毫秒");
            }
        },2000,TimeUnit.MILLISECONDS);
    }


    public void timerThree(){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {

//                throw new RuntimeException();
            }
        },1,SECONDS);
    }


    public void timerFour(){
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
//                System.out.println("任务4开始执行："+new Date());
            }
        },3,SECONDS);
    }
}
