package com.example.javabasic.thread;

public class BasicThread {

    public static void main(String[] args){
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println(Thread.currentThread().getName()+":"+"waiting for liftOff");
    }

}
