package com.example.javabasic.thread.await_signal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Car{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed(){
        //使用lock显式地加锁，不需要再使用synchronized关键字修饰方法
        lock.lock();
        try{
            waxOn = true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public void buffed(){
        lock.lock();
        try{
            waxOn = false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }


    public void waitForWaxing() throws InterruptedException{
        lock.lock();
        try{
            while (waxOn ==  false){
                condition.await();
            }
        }finally {
            lock.unlock();
        }
    }


    public void waitForBuffing() throws InterruptedException{
        lock.lock();
        try{
            while (waxOn==true){
                condition.wait();
            }
        }finally {
            lock.unlock();
        }
    }

}


class WaxOn implements Runnable{

    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println("Wax On!");
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


class WaxOff implements Runnable{

    private Car car;

    public WaxOff(Car c){
        car = c;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                car.waitForBuffing();
                System.out.println("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        }catch (InterruptedException e){
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending Wax Off task");
    }
}


public class WaxOnMatic2 {

    public static void main(String[] args) throws Exception{
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}
