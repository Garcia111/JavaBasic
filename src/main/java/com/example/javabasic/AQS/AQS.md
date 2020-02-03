ReentrantLock以及AQS的实现原理
    提到Java锁我们通常会想到synchronized关键字或者是Java Concurrent Util包下面的Lock
    那么Lock是如何实现的？当我们实例化一个ReententrantLock并且调用它的lock或者unlock的时候，这其中发生了什么？
    如果多个线程同时对一个锁进行lock或者unlock操作，这其中又发生了什么？

   什么是可重入锁？
    ReentrantLock是可重入锁，【可重入锁就是当前持有该锁的线程能够多次获取该锁，无需等待】。
    可重入锁通过ReentrantLock的一个内部类Sync的父类AbstractQueuedSynchronizer实现，简称AQS。

  什么是AQS
    AQS是JDK1.5提供的一个【基于FIFO等待队列】实现的一个用于实现【同步】的基础框架，Java.concurrent.util包里面的几乎
    锁的有关锁，多线程并发以及线程同步器等重要组件的实现都是基于AQS这个框架。
    AQS的核心思想是基于volatile int state这样一个属性同时配合Unsafe工具对其原子性的操作来实现对当前锁的状态进行修改。
    当state的值为0的时候，标识该Lock不被任何线程所占有。

  AQS的等待队列
    作为AQS的核心实现的一部分，AQS的等待队列是基于一个双向链表实现的，
    假设当前有三个线程Thread1 Thread2 Thread3同时去竞争锁，如果是Thread1获得了锁，Thread2 Thread3进入了等待队列。
    则队列中，HEAD节点不关联线程，后面两个节点分别关联Thread2 Thread3，他们将会按照先后顺序被串联在这两个队列上，
    这时候如果后面再有线程进来的话将会在后面继续入队。

    1）入队
    三个线程同时进来，他们会首先通过CAS去修改state的状态，如果修改成功，那么竞争成功，因此这个时候三个线程只有一个CAS成功，其他
    两个线程失败，也就是tryAcquire会返回false。













