package com.example.javabasic.thread.blocking_queue;

import org.hibernate.cfg.SecondaryTableSecondPass;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {

    private int queueSize = 10;

    //PriorityBlokingQueue本身是无界的，这里指定的是它的初始容量
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(queueSize);


    public static void main(String[] args){
        BlockingQueueTest test = new BlockingQueueTest();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }


    class Consumer extends Thread{

        @Override
        public  void run(){
            consume();
        }


//        private void consume(){
//            while (!Thread.interrupted()){
//                synchronized (queue){
//                    while (queue.size() == 0){
//                        try{
//                            System.out.println("队列空，等待数据");
//                            queue.wait();
//                        }catch (InterruptedException e){
//                            System.out.println("consumer wait interrupted");
//                            queue.notify();
//                        }
//                    }
//                    Integer consumeItem = queue.poll();
//                    queue.notify();
//                    System.out.println("消费一个元素,剩余："+queue.size()+"个元素");
//
//                }
//            }
//        }


        private void consume(){
            while (!Thread.interrupted()){
                try{
                    queue.take();
                    System.out.println("消费一个元素，剩余"+queue.size()+"个元素");
                }catch (InterruptedException e){
                    System.out.println("Consumer take interrupted");
                }
            }
        }

    }


    class Producer extends Thread{


        @Override
        public void run(){
            produce();
        }

        //使用普通的同步方式
//        private void produce(){
//            while (!Thread.interrupted()){
//                synchronized (queue){
//                    //只允许队列中存放10个元素
//                    while (queue.size() == queueSize){
//                        try{
//                            System.out.println("队列已满，等待consumer 消费");
//                            queue.wait();
//                        }catch (InterruptedException e){
//                            System.out.println("producer wait interrupted");
//                            queue.notify();
//                        }
//                    }
//                    Integer produceElement = new Random().nextInt(10);
//                    queue.offer(produceElement);
//                    System.out.println("producer生产一个元素,当前队列长度为："+queue.size());
//                    queue.notify();
//
//                }
//            }
//        }


        /**
         * 使用阻塞队列生产
         */
        private void produce(){
            while (!Thread.interrupted()){
                try{
                    queue.put(1);
                    System.out.println("producer生产一个元素,当前队列长度为："+queue.size());
                }catch (InterruptedException e){
                    System.out.println("producer wait interrupted");
                }
            }
        }

    }

}
