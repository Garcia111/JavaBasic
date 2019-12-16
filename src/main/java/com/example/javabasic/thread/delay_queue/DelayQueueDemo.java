package com.example.javabasic.thread.delay_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author：Cheng.
 * @date：Created in 15:49 2019/12/16
 */
class DelayedTask implements Runnable, Delayed{

    private static int counter;

    private final int id = counter++;

    //延迟时间
    private final int delta;

    //触发时间
    private final long trigger;

    /**用来按照添加顺序存放添加到延迟队列中的元素，以形成对照*/
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();


    public DelayedTask(int delayInMillseconds){
        delta = delayInMillseconds;
        //delta的时间单位是毫秒，而trigger存储的时间单位是纳秒，因此需要进行单位转换
        trigger = System.nanoTime()+NANOSECONDS.convert(delta,MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit){
        //希望使用的单位是作为null参数传递进来的，使用它将当前时间与触发时间之间的差转换为调用者要求的时间单位
        //而无需知道这些单位是什么，这是策略模式的一个简单示例，算法的一部分是作为参数传递进来的
        //todo 策略模式
        return unit.convert(trigger-System.nanoTime(),NANOSECONDS);
    }


    @Override
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if(trigger < that.trigger){
            return -1;
        }
        if(trigger > that.trigger){
            return 1;
        }
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this+" ");
    }

    @Override
    public String toString(){
        return String.format("[%1$-4d]",delta)+" Task "+id;
    }


    public String sumamry(){
        return "("+id+":"+delta+")";
    }

    public static class EndSentinel extends  DelayedTask{
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e){
            super(delay);
            exec = e;
        }

        @Override
        public void run(){
            //按照添加顺序逐个打印队列中的元素
            for(DelayedTask pt: sequence){
                System.out.println(pt.sumamry()+" ");
            }
            System.out.println();
            System.out.println(this+" Calling shutdownNow()");
            //EndSentinel类提供了一种关闭所有事物的途径，具体做法是将其放置为队列的最后一个元素，
            //exec执行完该元素的run()方法后，即调用exec.shutdownNow()
            exec.shutdownNow();
        }

    }
}


class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
        this.q = q;
    }


    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                //按照延迟顺序从DelayQueue中取出各个元素，从打印结果可以看出，是按照延迟时间从小到大逐个打印的
                q.take().run();
            }
        }catch (InterruptedException e){
            System.out.println("DelayedTaskConsumer interrupted");
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {

    public static void main(String[] args){
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        /**用于存放添加元素的延迟队列*/
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();

        for(int i=0;i<20;i++){
            queue.put(new DelayedTask(rand.nextInt(5000)));
        }
        //打印队列中的所有元素
        queue.add(new DelayedTask.EndSentinel(5000,exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }


}
