package xiangxue.concurrent.chap1.base.safeend;

/** 类说明：阻塞方法中抛出InterruptedException异常之后，如果需要继续中断，需要手动再中断一次
 * @author：Cheng.
 * @date：Created in 10:16 2020/1/19
 */
public class HasInterruptedException {

    private static class UseThread extends Thread{

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run(){
            while(!isInterrupted()){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    //捕获异常之后，会重新将中断标志位clear，因此此处打印isInterrupted()是false
                    System.out.println(Thread.currentThread().getName()+
                            " in InterruptedException interrupt flag is "
                            +isInterrupted());

                    //设置中断位之后，线程内部捕获到InterruptedException，然后会将中断标志位isInterrupted()重新设置为false，
                    //这是为了不立即中断线程，如果需要立即中断线程需要手动调用interrupt()重置中断标志位,这样留给了程序员释放资源的时间，
                    //可以在释放资源之后，再调用interrupt()终止线程
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()
                        +"I am extends Thread.");
            }
            System.out.println(Thread.currentThread().getName()+" interrupt flag is "+isInterrupted());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread endThread = new UseThread("HasInterruptException");
        endThread.start();
        Thread.sleep(500);
        endThread.interrupt();
    }
}
