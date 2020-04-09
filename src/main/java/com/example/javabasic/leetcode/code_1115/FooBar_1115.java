package com.example.javabasic.leetcode;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class FooBar_1115 {

    private int n;

    private Semaphore semaphoreFoo;
    private Semaphore semaphoreBar;

    public FooBar_1115(int n) {
        this.n = n;
        semaphoreFoo = new Semaphore(1);
        semaphoreBar = new Semaphore(0);
    }


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            //foo信号量为0
            semaphoreFoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            //bar信号量为1
            semaphoreBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphoreFoo.release();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Runnable fooRunnable = () -> System.out.println("foo");
        Runnable barRunnable = () -> System.out.println("bar");
        FooBar_1115 foobar = new FooBar_1115(3);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                foobar.foo(fooRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(()->{
            try {
                foobar.bar(barRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}
