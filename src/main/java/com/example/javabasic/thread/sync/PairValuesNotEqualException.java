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
 * 虚拟类持有一个同步的Pair List
 */
abstract class PairManager{
    AtomicInteger checkCounter = new AtomicInteger(0);
    protected Pair p = new Pair();
    private List<Pair> storage = Collections.synchronizedList(new ArrayList<Pair>());
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
        p.incrementY();store(getPair());
    }
}

class PairManager2 extends PairManager{

    @Override
    public void increment() {
        Pair temp;
        synchronized (this){
            p.incrementX();
            p.incrementY();
            temp = getPair();
        }
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
}







