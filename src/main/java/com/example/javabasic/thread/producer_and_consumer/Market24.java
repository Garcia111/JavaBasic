package com.example.javabasic.thread.producer_and_consumer;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Item{
    private final int itemNum;

    public Item(int itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public String toString() {
        return "Item" + itemNum;
    }
}

class Producer implements Runnable{

    private int count =0;

    Market24 market;

    public Producer(Market24 market) {
        this.market = market;
    }

    protected int getCount(){return count;}

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try{
                //生产者总共就生产100件商品
                while (count<100){
                    Item item = new Item(++count);
                    //queue，在不违反容量限制的情况下立即将元素插入到此队列中
                    if(market.storage.offer(item)){
                        System.out.println("Produced "+item);
                        synchronized (market.consumer){
                            market.consumer.notifyAll();
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(10);
                    synchronized (this){
                        //如果生产的产品数量超过接收者的缓存数量10时，则不再生产，wait
                        while (!(market.storage.size()<10)){
                            wait();
                        }
                    }

                }

                System.out.println("Produced "+count+"Items, Stopping producing");
                return;
            }catch (InterruptedException e){
                System.out.println("Producer interrupted");
                System.out.println("Produced "+count+" Items");
            }
        }
    }
}


class Consumer implements Runnable{
    int counsumed = 0;
    Market24 market;

    //消费者的消费的Item列表
    List<Item> cart = new ArrayList<Item>();


    public Consumer(Market24 market24) {
        this.market = market24;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    //当消费者消费的数量等于生产者生产的数量时，说明已经没有Item可以消费了，消费者需要wait
                    while(!(cart.size()<market.producer.getCount())){
                        wait();
                    }
                }
                //pool：从队列中取出队列头，如果队列为空则返回null
                if (cart.add(market.storage.poll())){
                    System.out.println("Consuming Item "+ ++counsumed);

                    synchronized (market.producer){
                        market.producer.notifyAll();
                    }
                }
            }

        }catch (InterruptedException e){
            System.out.println("Consumer interrupted");
            System.out.println("Consumed "+counsumed+" Items");
        }
    }
}



public class Market24 {
    //使用wait()和notifyAll()解决单个生产者 单个消费者问题，生产者不能溢出接收者的缓冲区，而这在生产者比
    //消费者速度快时完全有可能发生。如果消费者比生产者速度快，那么消费者不能读取多次相同数据，
    //不要对生产者和消费者的相对速度做任何假设。
    ExecutorService exec = Executors.newCachedThreadPool();
    Queue<Item> storage = new LinkedList<Item>();
    Producer producer = new Producer(this);
    Consumer consumer = new Consumer(this);

    public Market24(){
        exec.execute(producer);
        exec.execute(consumer);
    }

    public static void main(String[] args){
        new Market24();
    }


}
