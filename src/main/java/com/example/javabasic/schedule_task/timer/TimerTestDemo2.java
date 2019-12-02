package com.example.javabasic.schedule_task.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer不会捕获发生的异常，如果发生异常，则整个TimerTask线程都会取消，并且已经安排但是尚未执行的额任务也不会执行
 * @author：Cheng.
 * @date：Created in 16:41 2019/12/2
 */
public class TimerTestDemo2 {

    private Timer timer;
    private long start;


    public static void main(String[] args){
        TimerTestDemo2 timerDemo = new TimerTestDemo2();
        System.out.println("任务开始: " + timerDemo.start);
        //如果任务1 中发生了异常，任务2也不会继续执行，Timer是单线程的
        timerDemo.timerOne();
        timerDemo.timerTwo();
    }


    public void timerOne(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        },1000);
    }


    public void timerTwo(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务2开始执行："+new Date());
            }
        },3000);
    }

}
