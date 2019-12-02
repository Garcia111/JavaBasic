package com.example.javabasic.schedule_task.timer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author：Cheng.
 * @date：Created in 15:10 2019/12/2
 */

public class TimerTest {

    private Timer timer;
    private long start;

    public TimerTest(){
        this.timer = new Timer();
        start = System.currentTimeMillis();
    }

    public static void main(String[] args){
//        System.out.println("任务开始" +  new Date());
//        timerTest3();


        //任务1与开始时间间隔为1秒，而任务2与开始时间的时间间隔为3秒。
        // 然而，由于Timer在执行定时任务时只会创建一个工作线程，当工作线程因为某种原因而导致线程任务执行时间过长，
        // 超过了两个任务的间隔时间，则会出现以上情况。
        TimerTest timerTest = new TimerTest();
        System.out.println("任务开始" +  new Date());
        timerTest.timerOne();
        timerTest.timerTwo();
    }



    public static void timerTest1(){
        //安排在指定的时间执行指定的任务安排在指定的时间执行指定的任务
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,15);
        calendar.set(Calendar.MINUTE,30);
        calendar.set(Calendar.SECOND,0);

        final Date time = calendar.getTime();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("15：30执行一次任务"+new Date());
            }
        },time);
    }



    public static void  timerTest2(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2s后执行一次任务"+new Date());
            }
        },2000);
    }



    public static void timerTest3(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("在延迟指定时间后以指定的间隔时间循环执行定时任务"+new Date());
            }
        },2000,1000);
    }


    public void timerOne(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务1开始执行："+(System.currentTimeMillis()-start)+"毫秒");
                try{
                    Thread.sleep(4000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        },1000);
    }


    public void timerTwo(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务2开始执行："+(System.currentTimeMillis()-start)+"毫秒");
            }
        },3000);
    }
}
