package com.example.javabasic.thread.dead_lock;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 10:23 2019/12/13
 */
class ChopStick{
    private boolean taken = false;

    /**
     * 尝试取筷子，未取到则等待
     * @throws InterruptedException
     */
    public synchronized  void take() throws  InterruptedException{
        while (taken){
            wait();
        }
        taken = true;
    }


    public synchronized  void  drop(){
        taken = false;
        notifyAll();
    }
}

class Philosopher implements Runnable{

    private  ChopStick left;
    private  ChopStick right;
    private final int id;
    //哲学家停下来思考
    private  final int ponderFactor;
    private Random rand = new Random(47);

    private void pause() throws  InterruptedException{
        if(ponderFactor == 0)
            return;
        //ponderFactor为1时则进行思考
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
    }

    public Philosopher(ChopStick left, ChopStick right, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                System.out.println(this+" thinking");
                pause();

                //尝试拿起右筷子
                System.out.println(this+" grabbing right");
                right.take();
                //尝试拿起左筷子
                System.out.println(this+" grabbing left");
                left.take();
                System.out.println(this+" eating");
                pause();
                right.drop();
                left.drop();
            }
        }catch (InterruptedException e){
            System.out.println(this+" exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "id=" + id +
                '}';
    }
}

public class DeadlockingDiningPhilosophers {

    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 5;
        //ponder取第一个参数
        if(args.length >0){
            ponder = Integer.parseInt(args[0]);
        }
        //size取第二个参数，默认为5
        int size = 5;
        if(args.length >1){
            ponder  = Integer.parseInt(args[1]);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        //每个哲学家一根筷子
        ChopStick[] sticks = new ChopStick[size];

        for(int i=0; i<size; i++){
            sticks[i] = new ChopStick();
        }

        for(int i=0; i<size; i++){
            exec.execute(new Philosopher(sticks[i],sticks[(i+1)%size],i,ponder));
        }

        if(args.length == 3 &&args[2].equals("timeout")){
            TimeUnit.SECONDS.sleep(5);
        }else{
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }

}
