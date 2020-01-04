CAS机制：
    Atomic操作类的底层，正是利用了CAS机制，CAS是英文单词Compare And Swap的缩写，翻译过来就是比较并替换
    CAS机制当中使用了3个及基本操作数：内存地址V，旧的预期值A，要修改的新值B。
    当更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时：A=V，才会将内存地址V转换为要修改的新值B
                        如果比较发现预期值A与内存实际值V不相同，则提交失败；
                        提交失败后，线程会重新获取内存地址中的值，并重新计算想要修改的新值，这个重新尝试的过程称为【自旋】


CAS机制与Synchronized的区别是什么：
    Synchronized关键字会让没有得到锁资源的线程进入Blocked状态，而后在争夺到资源后恢复为Runnable状态，
    这个过程涉及到操作系统【用户模式】和【内核模式】的转换，代价比较高。
    尽管Java1.6为Synchronized做了优化，增加了偏向锁到轻量锁再到重量级锁的过渡。

    1.Synchronized属于悲观锁，悲观地认为程序中的并发情况严重，所以严防死守；
      CAS属于乐观锁，乐观地认为程序中的并发情况没有那么严重，所以让线程不断去尝试更新。
      这两种机制并没有绝对的好坏，关键是看使用场景。并发量高的情况下使用synchronized关键字，并发量低的情况下使用CAS


CAS的使用场景：
    很多地方都是用了CAS机制，Atomic系列类，以及Lock系列类的底层实现。甚至在Java1.6以上版本，
    Synchronized转换为重量级锁之前也会采用CAS机制。


CAS机制的缺点：
    1.CPU开销较大
    在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力；
    2.不能保证代码块的原子性：
    CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性。比如需要保证3个变量共同进行原子性的更新，就不得不适用Synchronized了。
    2.ABA问题
    ABA问题就是A改成了B，B又改成了A



CAS的底层是如何实现原子性的比较和更新一个值的？
    public final int incrementAndGet() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return next;
        }
    }
    //使用volatile修饰value，确保取得的值是内存中的最新值
    private volatile int value;
    public final int get() {
        return value;
    }

    可以看出CAS自旋是一个无限循环：
    1.获取当前值；
    2.当前值+1，计算出目标值
    3.进行CAS操作，如果成功则挑出循环，如果失败则重复上述操作；


  compareAndSet方法的实现：
  //expect是预想的内存中的最新值，update是此次需要更新的值
  public final boolean compareAndSSet(int expect, int update){
    return unsafe.compareAndSwapInt(this,valueOffset,expect,update);
  }

  private static final Unsafe unsafe = Unsafe.getUnsafe();
  private static final long valueOffset;

  static{
    try{
        valueOffset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
    }catch(Exception ex){ throw new Error(ex);}
  }


  unsafe
  Java语言不像C C++那样可以直接访问底层操作系统，但是JVM为我们提供了一个后门，这个后门就是unsafe，unsafe为我们提供了硬件级别的原子操作。
  valueOffset
  valueOffset对象，是通过unsafe.objectFiledOffset方法得到，所代表的是AtomicInteger对象value成员变量在内存中的偏移量。

  解决ABA问题：
  要做到严谨的CAS机制，我们在compare阶段不仅要比较期望值A和地址V中的实际值，还要比较变量的版本号是否是否一致。
  假设地址V中存储着变量值A，当前线程的版本号是01，线程1获得了当前值A和版本号01，想要更新为B，但是被阻塞了，
  这时候，内存地址V中的变量发生了很多次改变，版本号提升为03,但是变量值仍然是A，
  线程1恢复运行进行Compare操作，经过比较，线程1所获得的值和地址V的实际值都是A，但是版本号不相等，所以这一次更新失败。

  在Java当中，AtomicStampReference了就实现了用版本号做比较的CAS机制。


  1.Java语言CAS底层如何实现？利用unsafe提供了原子性操作方法；
  2.什么是ABA问题，怎么解决
    当一个值从A更新为B，又更新为A，普通CAS机制会误判，使用版本号比较可以有效解决ABA问题。






