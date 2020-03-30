package xiangxue.concurrent.concurrent_in_action.chap5;

import java.util.concurrent.CountDownLatch;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
public class TestHarness {

    /**
     * 为什么不在线程创建后就立即运行，却要自寻烦恼地使用TestHarness呢？或许因为我们想要计算在线程n倍并发的情况下
     * 执行一个任务的事假。如果我们简单的创建并启动线程，那么先启动的就比后启动的具有”领先优势“，并且根据活动线程数的
     * 增加或者减少，这样的竞争度也在不断改变。开始阀门让控制线程能够同时释放所有工作线程，结束阀门让控制线程能够等待最后
     * 一个线程完成任务，而不是顺序等待每一个线程结束。
     *
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        //开始阀门
        final CountDownLatch startGate = new CountDownLatch(1);
        //结束阀门
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i =0 ; i<nThreads; i++){
            Thread t = new Thread(){
                @Override
                public void run(){
                    try{
                        //每个工作线程要做的第一件事就是等待开始阀门打开
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            //每个工作线程的最后一个工作是为结束阀门减一
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignored){}
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        //开始阀门打开，每个工作线程开始执行
        startGate.countDown();
        //等待结束阀门打开，所有工作线程工作完成
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }
}
