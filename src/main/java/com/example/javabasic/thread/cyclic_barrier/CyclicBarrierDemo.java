package com.example.javabasic.thread.cyclic_barrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {


    /**
     * 每一个线程都具有一个循环屏障对象
     */
    static class TaskThread extends Thread{
        CyclicBarrier barrier;

        public TaskThread(CyclicBarrier barrier){
            this.barrier = barrier;
        }


        public void run(){
            try{
                Thread.sleep(1000);
                System.out.println(getName()+" 到达栅栏 A");
                barrier.await();
                System.out.println(getName()+" 冲破栅栏 A");

                Thread.sleep(2000);
                //第二次等待，循环屏障可以循环利用
                System.out.println(getName()+" 到达栅栏B");
                barrier.await();
                System.out.println(getName()+" 冲破栅栏 B");
            }catch (Exception e){
                e.printStackTrace();
            }

        }


        public static void main(String[] args){
            int threadNum = 5;
            CyclicBarrier barrier = new CyclicBarrier(threadNum,new Runnable(){

                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" 完成最后任务");
                }
            });

            for(int i=0; i<threadNum; i++){
                new TaskThread(barrier).start();
            }
        }


    }

}
