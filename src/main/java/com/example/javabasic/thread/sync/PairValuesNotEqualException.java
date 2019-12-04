package com.example.javabasic.thread.sync;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：Cheng.
 * @date：Created in 16:44 2019/12/3
 */


class Pair{

    //Pair不是线程安全的，因为其约束条件需要两个变量要维护成相同的值。
    private int x, y;

    public Pair(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Pair(){
        this(0,0);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    /**
     * 自增加操作不是线程安全的，需要从主内存中取，然后从本地内存写回主内存
     */
    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

    @Override
    public String toString(){
        return "x:"+x+",y:"+y;
    }


    public class PairValuesNotEqualException extends RuntimeException {

        public PairValuesNotEqualException(){
            super("Pair values not equal:"+ Pair.this);
        }
    }


    public void checkState(){
        if(x!=y){
            throw new PairValuesNotEqualException();
        }
    }
}


/**
 * PairManager类持有一个Pair对象，并控制对它的一切访问，保证在一个线程安全的环境中使用Pair对象
 * 模板方法设计模式：一些功能在基类中实现，并且其一个或多个抽象方法在派生类中定义。
 * 虚拟类持有一个同步的Pair List
 */
abstract class PairManager{
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());

    /**
     * 唯一的public方法是getPair()，是synchronized修饰的。
     * @return
     */
    public synchronized Pair getPair(){
        return new Pair(p.getX(),p.getY());
    }

    protected void store(Pair p){
        storage.add(p);
        try{
            TimeUnit.MILLISECONDS.sleep(50);
        }catch (InterruptedException ignore){}
    }


    public abstract void increment();
}


class PairManager1 extends PairManager{

    @Override
    public synchronized void increment() {
        p.incrementX();
        p.incrementY();
        store(getPair());
    }
}

class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        //宁愿使用同步控制块，而不是对整个方法进行同步控制的典型原因:使得其他线程能够更多的访问
        synchronized (this){
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
        //此方法已经用synchronized修饰，是线程安全的，因此可以放在同步代码块外面
        store(temp);
    }
}


class PairManipulator implements Runnable{

    private PairManager pm;

    public PairManipulator(PairManager pm){
        this.pm = pm;
    }


    @Override
    public void run() {
        while (true){
            pm.increment();
        }
    }


    @Override
    public String toString(){
        return "Pair:"+ pm.getPair() +" checkCounter = "+pm.checkCounter.get();
    }
}


class PairChecker implements Runnable{

    private PairManager pm;

    public PairChecker(PairManager pm){
        this.pm = pm;
    }

    @Override
    public void run() {
        while(true){
            pm.checkCounter.incrementAndGet();
            pm.getPair().checkState();
        }
    }
}







