package com.example.javabasic.thread.producer_and_consumer;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal{


    //订单号
    private final int orderNum;

    public Meal(int orderNum){
        this.orderNum = orderNum;
    }

    public String toString(){
        return "Meal "+orderNum;
    }

}


/**
 * 服务员
 */
class WaitPerson implements Runnable{

    private Restaurant restaurant;


    public WaitPerson(Restaurant r){
        restaurant = r;
    }



    @Override
    public void run() {
        try{
            //这里使用了两个锁，自己对象的锁用来判断是否符合条件等待，如果已经有了自己想要的条件发生，则从wait中跳出，然后
            // 获取对方的锁：用来执行自己应该做的任务（因为获取了对方的锁，对方现在是无法执行任务的），然后叫醒对方

            while(!Thread.interrupted()){
                //这里锁定的是当前的服务员，如果饭店当前没有饭，释放服务员对象上的锁
                synchronized (this){
                    while(restaurant.meal == null){
                        wait();
                    }
                }
                System.out.println("Waitperson got "+ restaurant.meal);

                //获取主厨的锁，消费meal，然后叫醒主厨做菜
                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    //通知被阻塞的主厨，现在已经没有菜了，你可以做菜了
                    restaurant.chef.notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("WaitingPerson interrupted");
        }
    }
}

class Chef implements Runnable{

    private Restaurant restaurant;

    private int count = 0;


    public Chef(Restaurant r){
        restaurant = r;
    }

    @Override
    public void run() {
        //将整个run()方法体放到一个try语句块中，可以使得run()都会被设计为可以有序的关闭。
        try{
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal != null){
                        //wait for the meal to be taken
                        wait();
                    }
                }

                if(++ count == 10){
                    System.out.println("Out of food, closing");
                    //调用shutdownNow()之后，任务并没有立即关闭，而是当任务试图进入一个可中断的阻塞操作时，
                    //这个中断只能跑出InterruptedException,因此这里调用shutdownNow之后，线程会继续向下执行
                    //输出OrderUp,然后在调用sleep()时，抛出InterruptedException
                    restaurant.exec.shutdownNow();

                    //如果加上下面这一行，则程序调用shutdownNow()之后则会立即跳出run()，不会继续向下执行
//                    return;
                }

                System.out.println("Order up!");

                //获取服务员对象锁，主厨做菜，然后唤醒等待服务员对象的锁的任务
                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }

                TimeUnit.MILLISECONDS.sleep(100);

            }
        }catch (InterruptedException e){
            System.out.println("Chef interrupted");
        }
    }
}


public class Restaurant {

    Meal meal;

    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public Restaurant(){
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args){
        new Restaurant();
    }

}
