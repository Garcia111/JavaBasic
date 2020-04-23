package com.example.javabasic.leetcode.code_1195;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.function.IntConsumer;

/**
 * @author：Cheng.
 * @since： 1.2.2
 */
public class FizzBuzz_AtomicInteger {

    private AtomicInteger state = new AtomicInteger(1);

    private int curNum = 1;

    private int n;

    public FizzBuzz_AtomicInteger(int n){
        this.n = n;
    }


    public void fizz(Runnable printFizz) throws InterruptedException{
        for(;;){

            //如果是1，则将state设置为0，如果设置失败则阻塞线程
            while (!this.state.compareAndSet(1,0)){
                //？？
                LockSupport.parkNanos(1L);
            }

            if(this.curNum > n){
                this.state.set(1);
                return;
            }

            if((this.curNum % 3==0) && (this.curNum % 5 !=0)){
                printFizz.run();
                this.curNum ++;
            }

            this.state.set(1);

        }
    }


    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(;;) {
            while (!this.state.compareAndSet(1, 0)) {
                LockSupport.parkNanos(1L);
            }

            if (this.curNum > n) {
                this.state.set(1);
                return;
            }

            if ((this.curNum % 3 != 0) && (this.curNum % 5 == 0)) {
                printBuzz.run();
                this.curNum++;
            }

            this.state.set(1);
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(;;) {
            while (!this.state.compareAndSet(1, 0)) {
                LockSupport.parkNanos(1L);
            }

            if (this.curNum > n) {
                this.state.set(1);
                return;
            }

            if ((this.curNum % 3 == 0) && (this.curNum % 5 == 0)) {
                printFizzBuzz.run();
                this.curNum++;
            }

            this.state.set(1);
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(;;) {
            while (!this.state.compareAndSet(1, 0)) {
                LockSupport.parkNanos(1L);
            }

            if (this.curNum > n) {
                this.state.set(1);
                return;
            }

            if ((this.curNum % 3 != 0) && (this.curNum % 5 != 0)) {
                printNumber.accept(this.curNum);
                this.curNum++;
            }

            this.state.set(1);
        }
    }



    public static void main(String[] args) {
        FizzBuzz_AtomicInteger fizzBuzz = new FizzBuzz_AtomicInteger(15);
        Runnable fizz = () -> System.out.println("fizz");
        Runnable buzz = () -> System.out.println("buzz");
        Runnable fizzbuzz = () -> System.out.println("fizzbuzz");

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                fizzBuzz.fizz(fizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.execute(() -> {
            try {
                fizzBuzz.buzz(buzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.execute(() -> {
            try {
                fizzBuzz.fizzbuzz(fizzbuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                fizzBuzz.number(value -> System.out.println(fizzBuzz.curNum));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
