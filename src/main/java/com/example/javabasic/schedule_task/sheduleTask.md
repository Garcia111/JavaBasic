1、Timer类
    Timer是一种工具，线程用其安排在后台线程中执行的任务，可以安排任务执行一次或者定期重复执行。每个Timer对象对应一个单独的
    后台线程，用于按顺序执行Timer所指定的所有定时任务。Timer任务应该快速完成，如果一个计时器任务花费了过多时间，它将会占用计时器的任务执行线程。
    当违规任务最重完成时，后续任务可能会扎堆并快速连续执行。
    
    在对计时器对象的最后一个实时引用消失并且所有未完成的任务都已经完成执行之后，计时器的任务执行线程将正常中止，并成为
    垃圾收集的对象。 默认情况下，任务执行线程不作为守护进程线程运行，因此它能够防止应用程序中止。 如果调用方希望快速
    终止计时器的任务执行线程，则调用方应该调用计时器的cancel方法。
    
    如果计时器的任务执行线程意外中止，则在计时器上调用任务的任何
    尝试都将会导致IllegalStateException,
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
    
    
 2.**ScheduleExecutorService**
    
    Java5.0引入了java.util.concurrent包，其中的一个并发实用程序是**ScheduledThreadPoolExecutor**，
    它是一个线程池，用于以给定的速率或延迟 重复执行任务。它实际上是计时器/时间任务组合的更通用的替代品。
    因为它允许多个服务线程，接受不同的时间单位，并且不需要子类化时间任务，只需要实现Runnable。
  
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
    @zone:时区，接收一个java.util.TimeZone#ID,Cron表达式会基于该时区解析，默认是一个空字符串，
    即去服务器所在地时区。比如一般使用Asia/Shanghai，该字段一般留空。
    4.fixedDelayAsString
        与fixedDelay相同，只是使用字符串的形式。唯一不同的是支持占位符，因此可以在配置文件中进行配置
    5.fixedRateAsString
        fixedRate的字符串形式，支持占位符。
    6.initialDelay:第一次延迟多长时间之后再执行
        @Scheduled(initialDelay = 1000,fixedRate=5000)
        第一次延迟1秒后执行，之后按fixedRate的规则每5s执行一次。
    7.initialDelayAsString
    https://www.jianshu.com/p/1defb0f22ed1
    
4.SpringBoot继承Quartz实现定时任务
    核心概念：调度器，任务和触发器
    
   1）Job接口
    
        只定义一个方法void execute(JobExecutionContext context)，可以通过实现该接口来定义需要执行的任务。
        Job用于表示被调度的任务，主要有两种类型的Job:
            1.stateless:无状态的；
            2.stateful:有状态的
        对于同一个Trigger来说，有状态的Job不能被并行执行，只有上一次触发的任务被执行完成之后，才能触发下一次执行。
            Job主要有两种属性：volatility:表示任务是否被持久化到数据库存储，为true持久化；
                            durability:表示在没有trigger关联的时候，任务是否被保留，为true保留。
            一个Job可以被多个trigger关联，但是一个trigger只能关联一个Job
        
   2) JobDetail
    
          Quartz每次调用Job时，都会重新创建一个Job实例，因此它不会接受一个Job实例，相反它接收一个Job实现类JobDetail
          JobDetail用来描述Job的名字 描述 关联监听器等信息，以便运行时通过newInstance()的反射机制实例化Job。
        
   3) Trigger类
   
          用来描述Job执行的时间触发规则，主要有SimpleTrigger和CronTrigger这两个子类。
          SimpleTrigger:当且仅当需要调度一次或者一固定时间间隔周期执行调度任务；
          CronTrigger:可以通过Cron表达式定义出各种复杂时间规则的调度方案，如周一到周五15:00——16:00执行调度等。
           一个Trigger只能对应一个作业实例，而一个作业实例可以对应多个触发器。
   
   4) Scheduller调度器
    
          调度器相当于一个容器，装载着任务和触发器，该类是一个接口，代表一个Quartz的独立运行容器，Trigger和JobDetail
          可以注册到Scheduler中，两者在Scheduler中拥有各组的组和名称，组和名称是Scheduler查找定位容器中某一对象的依据。
          https://www.cnblogs.com/wadmwz/p/10315481.html
    
    在quartz框架中，Job是通过反射创建出来的实例，不受Spring管理，Scheduler则交给Spring生成，在Spring-context-support
    jar包下 org.springframework.scheduling.quartz 包中有个SpringBeanJobFactory的类，Job实例通过该类的createJobInstance
    方法创建，根据Scheduler context、job data map 和 trigger data map填充其属性。但是创建的Job实例并没有被Spring管理，
   
    
   Quartz将Job保存在数据库中所需表的说明：
   1.qrtz_calendars:以Blob类型存储Quartz的Calendar信息；
   2.qrtz_cron_triggers:存储Cron Trigger，包括表达式和时区信息
   3.qrtz_fired_triggers:存储与已触发的Trigger相关的状态信息，以及相连Job的执行信息；
   4.qrtz_paused_trigger_grps:存储已经暂停的Trigger组的信息；
   5.qrtz_scheduler_state:存储少量的有关Scheduler的状态信息，和别的Scheduler实例（假如是用于一个集群中）
   6.qrtz_locks:存储程序的悲观锁的信息（假如使用了悲观锁）
   7.qrtz_job_details:存储每一个已配置的Job的详细信息；
   8.qrtz_job_listeners:存储有关已配置的JobListener的信息
   9.qrtz_simple_triggers:存储简单的Trigger，包括重复次数，间隔，以及已经触发的次数；
   10.qrtz_blog_triggers: Trigger作为Blob类型存储（用于Quartz用户用JDBC创建他们自己定制的Trigger类型，
        JobStore并不知道如何存储实例的时候）
   11.qrtz_trigger_listeners:存储已经配置的TriggerListener的信息；
   12.qrtz_triggers:存储已经配置的Trigger的信息 
    
   **quartz 持久化数据库表格字段解释**
   
建表,SQL语句在quartz-1.6.6\docs\dbTables文件夹中可以找到,介绍下主要的几张表： 
       
     1.表qrtz_job_details: 保存job详细信息,该表需要用户根据实际情况初始化 
       job_name:集群中job的名字,该名字用户自己可以随意定制,无强行要求 
       job_group:集群中job的所属组的名字,该名字用户自己随意定制,无强行要求 
       job_class_name:集群中个note job实现类的完全包名,quartz就是根据这个路径到classpath找到该job类 
       is_durable:是否持久化,把该属性设置为1，quartz会把job持久化到数据库中 
       job_data:一个blob字段，存放持久化job对象 

     2.表qrtz_triggers: 保存trigger信息 
       trigger_name: trigger的名字,该名字用户自己可以随意定制,无强行要求 
       trigger_group:trigger所属组的名字,该名字用户自己随意定制,无强行要求 
       job_name: qrtz_job_details表job_name的外键 
       job_group: qrtz_job_details表job_group的外键 
       trigger_state:当前trigger状态，设置为ACQUIRED,如果设置为WAITING,则job不会触发 
       trigger_cron:触发器类型,使用cron表达式 

    3.表qrtz_cron_triggers:存储cron表达式表 
       trigger_name: qrtz_triggers表trigger_name的外键 
       trigger_group: qrtz_triggers表trigger_group的外键 
       cron_expression:cron表达式 
       
    4.表qrtz_scheduler_state:存储集群中note实例信息，quartz会定时读取该表的信息判断集群中每个实例的当前状态 
       instance_name:之前配置文件中org.quartz.scheduler.instanceId配置的名字，就会写入该字段，如果设置为AUTO,quartz会根据物理机名和当前时间产生一个名字 
       last_checkin_time:上次检查时间 
       checkin_interval:检查间隔时间   
    
    
Quartz线程：
    在Quartz中，有两类线程，Scheduler调度线程和任务执行线程，其中任务执行线程通常使用一个线程池维护一组线程。
    
Scheduler调度线程主要有两个：
    1.执行常规调度的线程：
    常规调度线程轮询存储所有的trigger，如果有需要触发的trigger,即到达了下一次触发的时间，则从任务执行线程池获取一个空闲线程，
    执行与该trigger关联的任务。
    2. 执行misfired trigger的线程
    Misfire线程是扫描所有的trigger，查看是否有misfiredtrigger，如果有的话，根据misfire的策略，分别处理。
    （fire now OR wait for the next fire）
    
Quartz Job数据存储
    
    Quartz的trigger和job需要存储下来才能被使用，Quartz中有两种存储方式：
    1.RAMJobStore:将trigger和job存储在内存中；
    2.JobStoreSupport:基于jdbc将trigger和job存储到数据库中。
    RAMJobStore的存取速度非常快，但是由于其在系统被停止后所有的数据都会丢失，所以在集群中，必须使用JobStoreSupport。
    
    https://www.jianshu.com/p/171d39067e42
    

 Quartz集群相关数据库表
    Quartz发布包中包含了所有被支持的数据库平台的SQL脚本，这些SQL脚本存在于
    <quartz_home>/docs/dbTables目录下。
    1.qrtz_job_details:存储的是job的详细信息，包括description描述，is_durable是否持久化，job_data持久化对象等基本信息；
    2.qrtz_triggers:触发器信息，包括
                            job名；
                            description:触发器的描述等基本信息
                            start_time:开始执行时间
                            end_time：结束执行时间
                            prev_fire_time:上次执行时间
                            next_fire_time: 下次执行时间
                            trigger_type:触发器类型，simple和cron
                            trigger_state:执行状态，有waiting(等待) paused(暂停) acquired(运行中)
    3.qrtz_cron_triggers:保存cron表达式
    4.qrtz_scheduler_state: 存储集群中note实例信息，quartz会定时读取该表的信息判断集群中每个实例的当前状态
    5.qrtz_paused_trigger_grps:暂停的任务组信息
    6.qrtz_locks:悲观锁发生的记录信息
    7.qrtz_fired_triggers:正在运行的触发器信息
    8.qrtz_simple_triggers:简单的触发器详细信息
    9.qrtz_blob_triggers:触发器存为二进制对象类型
    10.qrtz_canlendars:以Blob类型存储Quartz的Canlendar信息




















    
    
    
    
    
    
    
    
    
    
    
    
    
    