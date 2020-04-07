package com.example.javabasic.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class ZeroEvenOdd_1116_CountDownLatch {
    private int n;

    private CountDownLatch zeroLatch;
    private CountDownLatch evenLatch;
    private CountDownLatch oddLatch;

    public ZeroEvenOdd_1116_CountDownLatch(int n) {
        this.n = n;
        this.zeroLatch = new CountDownLatch(0);
        this.evenLatch = new CountDownLatch(1);
        this.oddLatch = new CountDownLatch(1);
    }


    public void zero(IntConsumer intConsumer) throws InterruptedException {
        for(int i=1;i<=n; i++){
            zeroLatch.await();
            intConsumer.accept(0);
            zeroLatch = new CountDownLatch(1);
            if(i%2==0){
                //打印偶数
                oddLatch.countDown();
            }else{
                //打印奇数
                evenLatch.countDown();
            }
        }


    }

    public void even(IntConsumer intConsumer) throws InterruptedException {
        for(int i=1; i<=n; i+=2){
            evenLatch.await();
            intConsumer.accept(i);
            evenLatch = new CountDownLatch(1);
            zeroLatch.countDown();
        }
    }

    public void odd(IntConsumer intConsumer) throws InterruptedException {
        for(int i=2; i<=n; i+=2){
            oddLatch.await();
            intConsumer.accept(i);
            oddLatch = new CountDownLatch(1);
            zeroLatch.countDown();
        }
    }


    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s1 = br.readLine();
        int n = Integer.parseInt(s1);
        IntConsumer in = new IntConsumer() {
            @Override
            public void accept(int n) {
                System.out.print(n);
            }
        };

        ZeroEvenOdd_1116_CountDownLatch zeo = new ZeroEvenOdd_1116_CountDownLatch(n);


        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(() -> {
            try {
                zeo.zero(in);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        executor.execute(() -> {
            try {
                zeo.even(in);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        executor.execute(() -> {
            try {
                zeo.odd(in);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
