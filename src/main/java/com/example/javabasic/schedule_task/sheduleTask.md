1、Timer类
    Timer是一种工具，线程用其安排在后台线程中执行的任务，可以安排任务执行一次或者定期重复执行。每个Timer对象对应一个单独的
    后台线程，用于按顺序执行Timer所指定的所有定时任务。Timer任务应该快速完成，如果一个计时器任务花费了过多时间，它将会占用计时器的任务执行线程。
    当违规任务最重完成时，后续任务可能会扎堆并快速连续执行。
    
    在对计时器对象的最后一个实时引用消失并且所有未完成的任务都已经完成执行之后，计时器的任务执行线程将正常中止，并成为
    垃圾收集的对象。 默认情况下，任务执行线程不作为守护进程线程运行，因此它能够防止应用程序中止。 如果调用方希望快速
    终止计时器的任务执行线程，则调用方应该调用计时器的cancel方法。
    
    如果计时器的任务执行线程意外中止，则在计时器上调用任务的任何进一步尝试都将会导致IllegalStateException,
    就好像调用了计时器的cancel方法一样。
    
    【这个类是线程安全的，多个线程可以共享一个计时器对象，而不需要外部同步。】
    【此类不提供实时保证，它使用Object.wait(long)方法调度任务】
    
    在内部，它使用二进制堆来表示其任务的队列，因此调度任务的成本是O（log n）其中n是并发调度任务的数量。？？
    
    
    TimeTask是由Timer安排执行一次或者重复执行的任务。
    Timer提供了四个执行任务的重构方法
    1.schedule(TimerTask task, Date time) ——安排在指定的时间执行指定的任务；
    2.schedule(TimerTask task, Date firstTime, long period) ——安排指定的任务在指定的时间开始进行重复的固定延迟执行；
    3.schedule(TimerTask task, long delay) ——安排在指定延迟后执行指定的任务；
    4.schedule(TimerTask task, long delay, long period) ——安排指定的任务在指定的延迟后开始进行重复的固定速率执行。
    
    
Timer的缺陷：
    （1）Timer对调度的支持是基于绝对时间而不是相对时间，因此它对于系统时间的改变非常敏感；
    （2）Timer线程是不会捕获异常的，如果TimerTask抛出未检查的异常则会导致Timer线程终止，
        同时Timer也不会重新恢复线程的执行，它会错误的认为整个Timer线程都会取消，已经被安排但尚未执行的TimerTask也不会再执行了，
        新的任务也不能被调度。因此，如果TimerTask抛出未检查的异常，Timer将会产生无法预料的行为。
     (3)Timer类是单线程的,ScheduledExecutorService中的每个任务是并发执行的，每个调度任务都会分配到线程池中的
        一个线程去执行，任务是并发执行的，互不影响。
    使用ScheduleExecutorServic可以解决上述两个缺陷：https://www.cnblogs.com/zy-l/p/9178381.html
    
    
 2.ScheduleExecutorService   
    Java5.0引入了java.util.concurrent包，其中的一个并发实用程序是ScheduledThreadPoolExecutor，
    它是一个线程池，用于以给定的速率或延迟 重复执行任务。它实际上是计时器/时间任务组合的更通用的替代品。
    因为它允许多个服务线程，接受不同的实践单位，并且不需要子类化时间任务，只需要实现Runnable。
  
3.Spring Task: Spring3.0以后自带的task,可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单的多。
    在定时任务方法上添加@Scheduled，需要在主类上使用@EnableScheduling注解开启对定时任务的支持，然后启动项目创建任务类。
  
  1）默认springTask中，所有的定时任务是在同一个线程中串行执行的。
        如果有多个定时任务，如果这些任务中只有一个任务卡死，会导致其他任务无法执行。
   
  2）如果想将定时任务改为在多个线程中执行，需要创建线程池bean,并在配置文件上添加 @EanbleAsync 开启对异步事件的支持。
    然后在定时任务的类或者方法上添加@Async,这些定时任务即可在不同的线程中执行。

执行时间的配置：
    @Scheduled注解的属性可以用来设置任务的执行时间：
    1.fixedRate:定义一个按一定频率执行的定时任务；
    2.fixedDelay:定义一个按一定频率执行的任务，此属性可以配合initialDelay，定义任务延迟执行时间。
    3.cron：通过表达式来
    @zone:时区，
    https://www.jianshu.com/p/1defb0f22ed1