package com.example.javabasic.threadpool.sleep;

import java.util.Random;
import java.util.concurrent.*;

public class SleepPractice1 {

    //创建一个任务，它将睡眠1至10秒之间的随机数量的时间，然后显示它的睡眠时间并退出。创建并运行一定数量的这种任务。

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            Future<Integer> result = exec.submit(new SleepTask());
                try {
                    System.out.println(result.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
        }
        exec.shutdown();
    }

}

class SleepTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int sleepTime = random.nextInt(10);
        TimeUnit.SECONDS.sleep(sleepTime);
        return sleepTime;
    }
}
