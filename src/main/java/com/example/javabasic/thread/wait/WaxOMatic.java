package com.example.javabasic.thread.wait;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 16:12 2019/12/6
 */

class Car{

    //？？为什么这个变量不用volatile修饰呢，是因为两个线程不会同时访问这个变量吗
    private boolean waxOn = false;

    /**
     * 涂蜡
     */
    public synchronized void waxed(){
        waxOn = true;
        System.out.println("涂蜡已完成");
        notifyAll();
    }

    /**
     * 抛光
     */
    public synchronized  void buffed(){
        waxOn = false;
        System.out.println("抛光已完成");
        notifyAll();
    }

    /**
     * 等待上蜡,检查waxOn标志，为false则挂起，【锁被释放】，在这个锁变得重新可用之前，这个任务是不会被重新唤醒的
     * @throws InterruptedException
     */
    public synchronized void waitForWaxing() throws InterruptedException {
        //必须用一个条件包围wait()
        while (waxOn == false){
            wait();
        }
    }


    /**
     * 等待抛光
     * @throws InterruptedException
     */
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn == true){
            wait();
        }
    }
}

/**
 * 上蜡任务
 */
class WaxOn implements Runnable{

    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                System.out.println("Wax on!");
                //调用sleep()模拟涂蜡的时间
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax On task");
    }
}

/**
 * 抛光任务
 */
class WaxOff implements Runnable{

    private  Car car;

    public WaxOff(Car c){
        car = c;
    }

    @Override
    public void run(){
        try{
            while(!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Ending Wax Off task");
        }
    }
}


public class WaxOMatic {

    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(1);
        //中断所有由当前ExecutorService创建的线程
        exec.shutdownNow();
    }


}
