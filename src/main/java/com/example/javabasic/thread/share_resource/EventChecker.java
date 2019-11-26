package com.example.javabasic.thread.share_resource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker implements Runnable {

    private IntGenerator generator;

    private final int  id;

    public EventChecker(IntGenerator g, int ident){
        generator = g;
        id = ident;
    }


    @Override
    public void run() {
        while(!generator.isCanceled()){
            int val = generator.next();
            System.out.println("val:"+val);
            //校验下一个数是否是偶数
            if(val % 2 != 0){
                System.out.println(val+ "not even");
                generator.cancel();
                //取消所有EventCheckers，所有线程都可以访问cancel变量，
                // 如果cancel被设置为true，所有的线程进入run()的时候都会停止
            }
        }
    }


    public static void test(IntGenerator gp,int count){
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<count;i++){
            exec.execute(new EventChecker(gp,i));
        }
        exec.shutdown();
    }


    public static void test(IntGenerator gp){
        //创建10个线程
        test(gp,10);
    }
}
