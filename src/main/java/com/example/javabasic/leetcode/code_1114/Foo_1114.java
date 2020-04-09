package com.example.javabasic.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Foo_1114 {

    private boolean firstFinishedd;
    private boolean secondFinished;
    private final Object lock = new Object();


    public Foo_1114() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            firstFinishedd = true;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (!firstFinishedd) {
                lock.wait();
            }

            printSecond.run();
            secondFinished = true;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (!secondFinished) {
                lock.wait();
            }

            printThird.run();
        }
    }


    public static void main(String[] args) {
        int[] order = {2, 3, 1};
        Foo_1114 foo = new Foo_1114();

        ExecutorService executor = Executors.newCachedThreadPool();

        Runnable task1 = ()->System.out.println("one");
        Runnable task2 = ()->System.out.println("two");
        Runnable task3 = ()->System.out.println("three");


        for (int num : order) {
            if (num == 1) {
                executor.execute(() -> {
                    try {
                        foo.first(task1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else if (num == 2) {
                executor.execute(() -> {
                    try {
                        foo.second(task2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                executor.execute(() -> {
                    try {
                        foo.third(task3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }

    }
}