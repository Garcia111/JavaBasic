package com.example.javabasic.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：Cheng.
 * @date：Created in 8:45 2020/1/2
 */
public class CAS {

    private static AtomicInteger count = new AtomicInteger(0);


    public static void main(String[] args){
        for(int i=0; i<2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        Thread.sleep(10);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    for(int j=0; j<100; j++){
                        count.incrementAndGet();
                    }
                }
            }).start();
        }


        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("count = " + count.get());
    }














}
