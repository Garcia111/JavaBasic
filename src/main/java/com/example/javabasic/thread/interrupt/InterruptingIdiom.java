package com.example.javabasic.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 11:31 2019/12/6
 */

class NeedsCleanup{

    private final int id;

    public NeedsCleanup(int ident){
        id = ident;
        System.out.println("NeedsCleanup "+id);
    }

    public void cleanup(){
        System.out.println("Cleaning up "+id);
    }
}


class Blocked3 implements Runnable{

    private volatile double d = 0.0;

    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                NeedsCleanup n1 = new NeedsCleanup(1);
                try{
                    System.out.println("Sleeping");
                    //消耗一下时间
                    TimeUnit.SECONDS.sleep(1);

                    NeedsCleanup n2 = new NeedsCleanup(2);

                    try{
                        //消耗一下时间
                        System.out.println("Calculating");
                        for(int i=1;i<2500000;i++){
                            d = d+(Math.PI +Math.E)/d;
                        }
                        System.out.println("Finished time-consuming operation");
                    }finally {
                        n2.cleanup();
                    }
                }finally {
                    n1.cleanup();
                }
            }
            System.out.println("Exiting via while() test");
        }catch (InterruptedException e){
            System.out.println("Exiting via InterruptedException");
        }
    }
}


public class InterruptingIdiom {


    public static void main(String[] args) throws Exception{
//        if(args.length !=1){
//            System.out.println("usage: java InterruptingIdion delay-in-mS");
//            System.exit(1);
//        }

        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        t.interrupt();
    }

}
