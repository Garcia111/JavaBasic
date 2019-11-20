package com.example.javabasic.thread.thread_priority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimplePriorities implements Runnable {

    private int countDown = 5;

    private volatile double d;

    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public String toString(){
        return Thread.currentThread()+":"+countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while(true){

            //没有加入这些复杂运算的话，就看不到设置优先级的效果。有课这些运算，就能看到
            //高优先级的线程被调度器优先选择，因为这里运算时间足够的长，因此线程调度机制才来得及介入，
            //交换任务并关注优先级，使得最高优先级线程被优先选择
            for(int i=1;i<100000;i++){
                //做了100000次此运算
                 d+=(Math.PI+Math.E)/(double)i;

                 if(i%1000 == 0){
                     Thread.yield();
                 }
            }
            System.out.println(this);
            if(--countDown ==0)
                return;
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }


}
