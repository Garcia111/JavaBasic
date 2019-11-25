package com.example.javabasic.thread.join;

public class Joining {

    public static void main(String[] args){
        Sleeper
                sleepy = new Sleeper("Sleepy",1500),
                grumpy = new Sleeper("Grumpy",1500);

        Joiner
                dopey = new Joiner("Dopey",sleepy),
                doc = new Joiner("DOC",grumpy);
        grumpy.interrupt();
    }

}

/**
 * sleeper是一个很简单的线程，执行的任务就是睡眠一定的时间
 */
class Sleeper extends Thread{
    private int duration;

    public Sleeper(String name,int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }


    public void run(){
        try{
            sleep(duration);
        }catch (InterruptedException e){
            System.out.println(getName()+" was interrupted."+
                    //异常被捕获时将会清理interrupt()标志，所以即使已经被中断了，但是下面打印的是否被中断的标志为false
            "isInterrupted():"+isInterrupted());
            return;
        }
        System.out.println(getName()+" has awakened");
    }
}

/**
 * 此线程的任务就是使其中的Sleeper任务对象插队，先执行
 */
class Joiner extends Thread{
    private Sleeper sleeper;

    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run(){
        try{
            sleeper.join();
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
        System.out.println(getName()+" join completed");
    }
}
