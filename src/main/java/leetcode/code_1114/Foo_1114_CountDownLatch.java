package leetcode.code_1114;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class Foo_1114_CountDownLatch {
    private CountDownLatch countDownLatchA ;
    private CountDownLatch countDownLatchB ;

    public Foo_1114_CountDownLatch() {
        this.countDownLatchA = new CountDownLatch(1);
        this.countDownLatchB  = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        countDownLatchA.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        countDownLatchA.await();
        printSecond.run();
        countDownLatchB.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        countDownLatchB.await();
        printThird.run();
    }

    public static void main(String[] args) {
        int[] order = {2, 1, 3};
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
