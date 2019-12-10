package com.example.javabasic.thread.blocking_queue;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：Cheng.
 * @date：Created in 9:59 2019/12/10
 */
public class MyArrayBlockingQueue<E> extends AbstractQueue<E>
        implements BlockingQueue<E>, Serializable {

    /**
     * 使用数组存储元素，并且此数据是final修饰的
     */
    final Object[] items;

    /**
     * 消费者取出元素的数组下标，因为ArrayBlockingQueue是FIFO的，所以这个takeIndex指向队首
     */
    int takeIndex;

    /**
     * 生产者添加元素的数组下标，指向队尾
     */
    int putIndex;

    /**
     * 队列中元素的数量
     */
    int count;

    /**
     * 使用重入锁控制对队列中元素的访问
     */
    final ReentrantLock lock;

    /**
     * 等待消费的条件,非空可以消费
     */
    private final Condition notEmpty;

    /**
     * 等待生产的条件，未满可以生产
     */
    private final Condition notFull;

    /**
     * 构造函数1
     * capacity:队列的长度
     * 默认ArrayBlockingQueue为非公平的
     *
     * @return
     */
    public MyArrayBlockingQueue(int capacity) {
        this(capacity, false);
    }


    public MyArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        //数组中存储的元素都转型为Object了。。。。
        this.items = new Object[capacity];
        //创建了一个非公平的可重入锁
        lock = new ReentrantLock(false);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }


    /**
     * @param capacity
     * @param fair
     * @param c        使用集合中的元素对队列进行初始化
     */
    public MyArrayBlockingQueue(int capacity, boolean fair, Collection<? extends E> c) {
        this(capacity, fair);


        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            int i = 0;
            try {
                for (E e : c) {
                    checkNotNull(e);
                    items[i++] = e;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(e);
            }
            count = i;
            putIndex = (i == capacity) ? 0 : i;
        } finally {
            lock.unlock();
        }
    }

    private static void checkNotNull(Object v) {
        if (v == null){
            throw new NullPointerException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 从队尾插入元素，插入成功返回true;插入失败返回false
     * @param e
     * @return
     */
    @Override
    public boolean offer(E e) {
        checkNotNull(e);
        ReentrantLock lock = this.lock;
        lock.lock();
        try{
            if(count == this.items.length){
                //队列已满，返回false
                return false;
            }else{
                enqueue(e);
                return true;
            }
        }finally {
            lock.unlock();
        }
    }

    private void enqueue(E e){
        final Object[] items = this.items;
        items[putIndex++] = e;
        putIndex = (putIndex == items.length)? 0 : putIndex;
        count++;
        //为什么这里是signal 而不是signalAll()??
        notEmpty.signal();
    }


    private E dequeue(){
        final Object[] items = this.items;
        E e = (E)items[takeIndex];
        items[takeIndex++]=null;
        takeIndex = takeIndex %items.length;
        count--;
        notFull.signal();
        return e;
    }


    /**
     * 向队尾插入元素，如果队列满了则等待
     * @param e
     * @throws InterruptedException
     */
    @Override
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        ReentrantLock lock = this.lock;
        lock.lock();
        try{
            while(count == this.items.length){
                //condition.await
                notFull.await();
            }
            enqueue(e);
        }finally {
            lock.unlock();
        }
    }


    /**
     * 向队尾插入元素，如果队列已满，则等待一段时间。当时间期限达到时，如果还未插入成功，返回false，否则返回true
     * @param e
     * @param timeout
     * @param unit
     * @return
     * @throws InterruptedException
     */
    @Override
    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        checkNotNull(e);
        //将时间期限转换为纳秒,为什么一定要转换为纳秒呢？？
        long nanos = unit.toNanos(timeout);
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while(count == items.length){
                if(nanos<=0){
                    return false;
                }
                notFull.awaitNanos(nanos);
            }
            enqueue(e);
            return true;
        }finally {
            lock.unlock();
        }
    }


    /**
     * 从队首取出元素，如果队列为空，则等待
     * @return
     * @throws InterruptedException
     */
    @Override
    public E take() throws InterruptedException {
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try{
            while (count == 0 ){
                notEmpty.await();
            }
            E e = dequeue();
            return e;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return null;
    }

    @Override
    public int remainingCapacity() {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c) {
        return 0;
    }

    @Override
    public int drainTo(Collection<? super E> c, int maxElements) {
        return 0;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }
}
