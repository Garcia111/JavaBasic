package com.example.javabasic.leetcode;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class FooBar_1115_lock {

    private int n;

    private volatile boolean fooFinished = false;
    private volatile boolean permitFoo = true;
    private final Lock lock = new ReentrantLock(true);

    public FooBar_1115_lock(int n) {
        this.n = n;
    }


    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (permitFoo) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    i++;
                    permitFoo = false;
                }
            } finally {
                lock.unlock();
            }


        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n;) {
            lock.lock();
            try {
                if (!permitFoo) {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    i++;
                    permitFoo = true;
                }
            } finally {
                lock.unlock();
            }

        }

    }


    public static void main(String[] args) throws InterruptedException {
        Runnable fooRunnable = () -> System.out.println("foo");
        Runnable barRunnable = () -> System.out.println("bar");
        FooBar_1115_lock foobar = new FooBar_1115_lock(3);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                foobar.foo(fooRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                foobar.bar(barRunnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }


}
