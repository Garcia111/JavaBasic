package com.example.javabasic.thread.daemon;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable{
    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        for(int i=0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            //将此线程设置为守护线程
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500);
        //为什么守护线程被创建了好几遍呢？
    }


}
