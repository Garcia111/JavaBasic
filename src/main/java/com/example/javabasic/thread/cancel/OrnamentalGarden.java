package com.example.javabasic.thread.cancel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 16:48 2019/12/4
 */
class Count{
    private int count = 0;
    private Random rand = new Random(47);


    public synchronized  int increment(){
        int temp = count;
        //todo
        //是为了腾出让步的时间？？？？
        if(rand.nextBoolean()){
            Thread.yield();
        }
        return (count = ++temp);
    }

    public synchronized int value(){return count;}
}


/**
 * 花园入口
 */
class Entrance implements Runnable{

    private static Count count = new Count();

    private static List<Entrance> entrances  = new ArrayList<Entrance>();

    private int number = 0;

    private final int id;

    //是一个布尔标志，只会被读取和赋值，所以不需要同步对其的访问
    private static volatile boolean canceled = false;

    public static void cancel(){canceled = true;}

    public Entrance(int id){
        this.id = id;
        entrances.add(this);
    }

    @Override
    public void run() {
        while(!canceled){
            synchronized (this){
                //进入一个人
                ++number;
            }
            System.out.println(this+" Total: "+count.increment());

            try{
                TimeUnit.MILLISECONDS.sleep(100);
            }catch (InterruptedException e){
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping "+this);
    }

    public synchronized  int getValue(){return number;}

    @Override
    public String toString(){
        return "Entrance "+id+": "+getValue();
    }


    public static int getTotalCount(){return count.value();}

    public static int sumEntrances(){
        int sum = 0;
        for(Entrance entrance:entrances){
            sum += entrance.getValue();
        }
        return sum;
    }
}

public class OrnamentalGarden {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = new ThreadPoolExecutor(5,20,60L,
                TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        //该花园一共有5个入口
        for(int i=0;i<5;i++){
            exec.execute(new Entrance(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();

        //ExecutirService.awaitTermination()等待每个任务结束，如果所有的任务在超时时间达到之前全部结束，则
        //返回true，否则返回false，表示所有的任务都已经结束了。
        if(!exec.awaitTermination(250,TimeUnit.MILLISECONDS)){
            System.out.println("Some tasks were not terminated!");
        }
        System.out.println("Total: "+Entrance.getTotalCount());
        System.out.println("Sum of Entrances: "+Entrance.sumEntrances());
    }

}
