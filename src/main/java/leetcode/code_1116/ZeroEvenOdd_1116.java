package leetcode.code_1116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class ZeroEvenOdd_1116 {
    private int n;

    public ZeroEvenOdd_1116(int n) {
        this.n = n;
    }

    Semaphore z = new Semaphore(1);
    Semaphore e = new Semaphore(0);
    Semaphore o = new Semaphore(0);

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            //获得zero信号量许可，信号量值变为0，可以打印0
            z.acquire();
            printNumber.accept(0);
            if ((i & 1) == 0) {
                //释放偶数信号量许可，偶数信号量变为1，允许持有偶数信号量许可的线程打印奇数
                o.release();
            } else {
                //释放奇数信号量许可，奇数信号量变为1，允许持有奇数信号量许可的线程打印偶数
                e.release();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            //获取奇数信号量许可，奇数信号量为-1，线程等待获取奇数信号量许可打印奇数
            e.acquire();
            printNumber.accept(i);
            //zero信号量值+1,允许打印0
            z.release();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            //获取偶数信号量许可，偶数信号量-1，线程等待获取偶数信号量许可打印偶数
            o.acquire();
            //打印奇数
            printNumber.accept(i);
            //zero信号量+1，打印0
            z.release();
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

        ZeroEvenOdd_1116 zeo = new ZeroEvenOdd_1116(n);


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
