package com.example.javabasic.thread.daemon;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class DaemonsDontRunFinally {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.MILLISECONDS.sleep(200);

        //当最后一个非后台线程终止时，后台线程会”突然“终止。因此，一旦main()退出，
        // JVM就会立即关闭所有的后台线程
    }
}


class ADaemon implements Runnable {
    @Override
    public void run() {
        try{
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        }finally {
            System.out.println("This should always run?");
        }

    }
}