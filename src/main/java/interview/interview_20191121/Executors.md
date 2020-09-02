为什么阿里巴巴要禁用Executors创建线程池:https://mp.weixin.qq.com/s/N-mO_AU9upr00P2bWfJyBw

   线程池
   
        通过线程池管理一组工作线程，通过线程池复用线程有以下几点优点：
        1.减少资源创建----减少内存开销，创建线程会占用内存；
        2.降低系统开销----创建线程需要时间，会延迟处理的请求，增加响应时间
        3.提高稳定性------避免无线创建线程引起的OutOfMemoryError
        
   Executors创建线程池的方式：
   
        根据返回的对象类型，创建线程池可以分为三类：
        1.创建返回ThreadPoolExecutor对象；
        2.创建返回ScheduleThreadExecutor对象
        3.创建返回ForkJoinPool对象
    
   ThreadPoolExecutor对象
   
        使用Executors创建ThreadPoolExecutor对象 和我们手动创建ThreadPoolExecutor的区别就是前者不需要自己传构造函数的参数。
        ThreadPoolExecutor的构造函数一共有四个，但是最终调用的都是同一个：
        
        public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler)    
                          
        .corePoolSize==>线程池核心线程数量
        .maxmumPoolSize==>线程池最大数量
        .keepAliveTime==>空闲线程存活时间
        .unit==>时间单位
        .workQueue==>线程池所使用的缓冲队列
        .threadFactory==>线程池创建线程使用的工厂
        .handler==>线程池对拒绝任务的处理策略？？
        
        
        线程池执行任务逻辑与线程池参数的关系，这个连接里面的图是在是画的太好了，瞬间明白了参数的意义
        1.提交任务
        2.判断核心线程数是否已满
            否--创建核心线程执行任务
            是--判断队列是否已满
                否--将任务添加到队列，不会创建新的线程
                是--通过线程最大数量判断线程池是否已满
                    否--创建非核心线程执行任务
                    是--按照策略handler处理无法执行的任务
                    
   Executors创建返回ThreadPoolExecutor对象的方法有三种：
    
    1.Executors.newCachedThreadPool-----创建可缓存的线程池；
    2.Executors.newSingleThreadPool-----创建单线程的线程池
    3.Executors.newFixedThreadPool------创建固定长度的线程池
    
   1)Executors.newChachedThreadPool
    
    public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
                                  
    CachedThreadPool是一个根据需要创建新线程的线程池
        .corePoolSize--0：核心线程池的数量为0；
        .maximumPoolSize--Integer.MAX_VAlUE:可以认为最大线程数量是无限的；
        .keepAliveTime--60L
        .unit--秒：这里设置的线程的存活时间为60秒
        .workQueue--SynchronousQueue,是一个不存储元素的队列，可以理解为队列里面永远是满的
        
        当一个任务提交时，corePoolSize为0不会创建核心线程，而线程队列永远都是满的，因此最终会创建非核心线程
        来执行任务。对于非核心线程空闲60s时将会被回收，因为Integer.MAX_VALUE非常大，因此可以认为可以无限创建线程
        在资源有限的情况下容易引起OOM异常。
        
        
   2）SingleThreadPool    
        
      public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
    
    corePoolSize--1:核心线程数为1
    maxmumPoolSize--1:只可以创建一个非核心线程
    keepAliveTime--0:
    unit:毫秒
    workQueue--LinkedBlockingQueue
    
    当一个任务提交时，首先会创建一个核心线程来执行任务，如果超过核心线程的数量，将会放入队列中。
    因为LinkedBlockingQueue是长度为Integer.MAX_VALUE的队列，可以认为你是无界队列，
    因此往队列中可以插入无限多的任务，在资源有限的情况下很容易引起OOM异常。
    同时因为是无界队列，maxmumPoolSize和keepAliveTime参数将无效，压根就不会创建非核心线程。
    也就是，创建一个核心线程，其他的任务全部压入队列。
   
    
   3）Executors.newFixedThreadPool 
        
     public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
        }
    
    FixedThreadPool是固定核心线程的线程池，固定核心线程数由用户传入：
    1.corePoolSize:核心线程数量由用户传入；
    2.maxmumPoolSize:最大线程数量由用户传入；
    3.keepAliveTime:0L
    4.unit:毫秒
    5.workingQueue:LinedBlockingQueue
    
    它和SingleThreadExecutor类似，唯一的却别就是核心线程的数量不同。由于同样使用了LinkedBlockingQueue，
    所以超过核心线程数量的任务都会被压入队列，不会创建非核心线程。因此，在资源有限的情况下很容易引起OOM异常。
    
    
   总结：
    FixedThreadPool和SingleThreadPool:允许的请求队列长度为Integer.MAX_VALUE，可能会造成堆积大量请求，从而引起OOM异常。
    CachdeThreadPool:允许创建的线程数量为Integer.MAX_VALUE，可能会创建大量线程，从而引起OOM异常。
    
    这就是为什么禁止使用Execcutors去创建线程池，而是推荐自己去手动创建ThreadPoolExecutor的原因。
    
    
    如何定义线程池参数