package com.example.javabasic.leetcode.code_1117;


import java.util.concurrent.*;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class H2O {

    //h信号量为2，保证每次只有两个打印h的线程在栅栏处等待
    private Semaphore h = new Semaphore(2);
    //o信号量为1，保证每次有一个打印o的线程在栅栏处等待
    private Semaphore o = new Semaphore(1);
    private CyclicBarrier barrier = new CyclicBarrier(3);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        //两个打印h的线程可以获取信号量许可
        h.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        h.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        o.release();
    }


    public static void main(String[] args) {

        Runnable h = () -> System.out.print("H");
        Runnable o = () -> System.out.print("O");
        H2O h2O = new H2O();
        ExecutorService executor = Executors.newCachedThreadPool();


        String s = "HHHH";
        char[] chars = s.toCharArray();
        //有几个字符就创建几个线程
        for(char c: chars){
            if(c=='O'){
                executor.execute(() -> {
                    try {
                        h2O.oxygen(o);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }else{
                executor.execute(() -> {
                    try {
                        h2O.hydrogen(h);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

    }
}
