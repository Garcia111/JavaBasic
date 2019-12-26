package xiangxue.concurrent.chap1;

/**
 * @author：Cheng.
 * @date：Created in 15:44 2019/12/26
 */
public  class Runnable1 implements Runnable{

    private boolean cancel;

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName:" + threadName +", isInterrupted:"+ Thread.currentThread().isInterrupted());
        while (!cancel){
//        while (!Thread.currentThread().isInterrupted()){
            System.out.println("threadName: "+ threadName+" is running");;
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                System.out.println("sleep is interrupted");
                //为什么发生中断了，但是打印中断标志位，打印结果还是false呢？
                //这是因为，如果一个线程处于了阻塞状态（如线程调用了thread.sleep() thread.join thread.wait等）则在线程
                //检查中断标识时如果发现中断标示为true,则会在这些阻塞方法调用处抛出InterruptedException异常，并且在抛出异常后
                //会立即将线程的中断标识位清楚，重新设置为false
                System.out.println("isInterrupted after while:"+Thread.currentThread().isInterrupted());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable1 runnable1 = new Runnable1();
        Thread endRunnable1 = new Thread(runnable1);
        endRunnable1.setName("endRunnable1");
        endRunnable1.start();
        Thread.sleep(40);
//        endRunnable1.interrupt();

        //不建议自定义一个取消标志来中止线程的执行，因为run方法里有阻塞调用（sleep join wait等）时会无法很快检测到自定义取消标志位，
        //线程必须从阻塞调用返回后，才会检查这个取消标志标志，这种情况下，使用中断会更好，因为：
        //1.一般的阻塞方法，如sleep等 本身就支持中断的检查
        //2.检查中断位的状态和检查取消标志位没有什么区别，用中断位的状态还可以避免声明取消标志位，减少资源的消耗
        runnable1.setCancel(true);
    }

}
