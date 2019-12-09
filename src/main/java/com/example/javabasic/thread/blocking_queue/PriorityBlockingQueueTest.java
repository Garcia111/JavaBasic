package com.example.javabasic.thread.blocking_queue;

import java.util.PriorityQueue;

public class PriorityBlockingQueueTest {

    private int queueSize = 10;

    //PriorityBlokingQueue本身是无界的，这里指定的是它的初始容量
    //https://www.cnblogs.com/dolphin0520/p/3932906.html
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);


    class Consumer extends Thread{

        @Override
        public  void run(){
            consume();
        }


        private void consume(){
            while (true){

            }
        }

    }

}
