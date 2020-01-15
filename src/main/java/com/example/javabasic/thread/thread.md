并发
   1.使用Runnable接口定义任务
    线程可以驱动任务，这可以由Runnable接口来提供。
    任务的run方法通常总会有某种形式的循环，使得任务一直运行下去直到不再需要，所以要设定跳出循环的条件。
    通常，run()将会被写成无限循环的形式，这就意味着，除非有某个条件使得run()终止，否则它将永远运行下去。
    
    调用Thread.yield()-----当前线程的当前任务已经完成，CPU可以切换给其他任务执行一段时间。
    要实现线程行为，必须显示地将一个任务附着到线程上。
    
    
  2.Thread类
    将Runnable对象转换为线程的方式是将其提交给一个Thread构造器。
    如果在你的机器上有多个处理器，线程调度器将会在这些处理器之间默默地分发线程。
    
    在main()创建Thread对象时，它并没有捕获任何对这些Thread 对象的引用。Thread对象的引用与普通对象的引用不同，
    每个Thread对象都注册了它自己，因此它自己存在一个对它的引用，而且在该线程的任务退出其run()方法并死亡之前，
    垃圾回收器都无法清除它。因此，一个线程会创建一个单独的执行线程，在对start()的调用完成之后，
    线程仍旧会继续存在。
    
  
  3.使用Executor
    Java.util.concurrent包中的执行器Executor将为你管理Thread对象，Executor在客户端和任务执行之间提供了一个
    间接层，与客户端直接执行任务不同，这个中介对象将执行任务。Executor允许你管理异步任务的执行，而无须显示地管理线程的
    生命周期。
    【命令设计模式】
     shudown()方法-----可以防止新的任务被提交给这个Executor，当前线程将继续运行在shutdown()被调用
     之前提交的所有任务。当调用了shutdown方法之后，即使再给ExecutorService添加新的任务，也不会再
     继续创建新的线程来执行此新的任务。
     
     使用FixedThreadPool
     可以一次性预先执行代价高昂的线程分配，因而也就可以限制线程的数量了。
     这样可以节省时间，因为你不用为每个任务都固定地付出创建线程的开销。？？？
     
     使用CachedThreadPool
     CachedThreadPool在程序执行过程中通常会创建与所需数量相同的线程，
     然后在它回收旧线程时停止创建新线程。
     
     使用SingleThreadExecutor
     SingleThreadExecutor就像是线程数量为1的FixedThreadPool。
     如果像SingleThreadExecutor提交了多个任务，那么这些任务将会排队，
     每个任务都会在下一个任务开始之前运行结束，所有的任务将会使用相同的线程。
     SingleThreadExecutor会序列化所有提交给它的任务，并且会维护它自己的悬挂任务队列。
     
     
     
   4.Callable 从任务中返回值
    Runnable是执行工作的独立任务，但是它不会返回任何值，执行完完事儿。
    使用Callable接口，可以在任务完成时返回一个值。
    Callable是一种具有类型参数的泛型，它的类型参数表示的是从方法call()中返回的值，并且
    必须使用ExecutorService.submit()方法调用call()方法。
    
    Submi()的Future结果对象
    submit()方法会产生Future对象，用Callable返回结果的特定类型进行了参数化。
    可以使用isDone()来查询Future是否已经完成，当任务完成时，Future对象会具有一个结果，可以调用get()来获取这个结果。
    如果你不用isDone()进行检查，就直接调用get()，这种情况下，get()将会阻塞，直至结果准备就绪。
    
    //todo 这块需要更多的理解和练习
    
  5.休眠 sleep()
  sleep()方法将使任务中止执行给定的时间。对sleep()的调用可能跑出InterruptedException
  
  
    【如果你必须控制任务执行的顺序，那么最好的押宝就是使用同步控制，或者在某些情况下，压根不使用线程，
    但是要编写自己的协作例程，这些例程将会按照指定的顺序在相互之间传递控制权】？？
    
  6.优先级
    线程的优先级将该线程的重要性传递给了调度器。
    尽管CPU处理现有线程集的顺序是不确定的，但是调度器倾向于让优先权最高的线程优先执行。
    优先级较低的线程执行的频率较低。
    【在绝大多数时间里，所有的线程都应该以默认的优先级运行。试图操纵线程优先级通常是一种错误】
    
    最常用的有MAX_PRIORITY  NORM_PRIORITY 和MIN_PRIORITY三种级别。
   
  7.yield()让步
    调用yield()方法意味着，知道run()已经完成的差不多了，按时可以让别的线程使用CPU了，
    这个暗示由yield()发出，【**这仅仅是一个暗示，没有任何机制保证它将会被采纳**】。
    当调用yield()时，你也是在建议具有相同优先级的其他线程可以运行。
    
    值得注意的是yield()经常被误用。 
    
  8.后台线程
    后台线程是指在程序运行的时候提供一种通用服务的线程，并且这种线程不属于程序中不可或缺的部分。
    当所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台线程。
    反过来说，只要有任何非后台线程还在运行，程序就不会终止。
    没有了守护的人，守护线程也就默默死去了，呜呜呜~~~

    【只有在线程未启动的时候才能设置该线程是否为守护线程，否则会抛出异常】

    如果一个线程是后台线程，那么它创建的任何线程将会被自动设置成后台线程,即使这些被创建的线程没有被显式地使用setDaemon()设置为守护线程。
    【后台线程在不执行finally子句的情况下就会终止run()方法。】
    
    //todo 修改SimplePriorities，使得定制的ThreadFactory可以设置线程的优先级。


  9.加入一个线程 join---插队
    一个线程可以在其他线程之上调用join()方法，其效果是等待一段时间直到第二个线程结束才能继续执行。
    相当于插队，如果某个线程在另外一个线程t上调用t.join()，此线程将会被挂起，直到目标线程t结束才恢复。
    
    也可以在调用join()时带上一个超时参数（毫秒 纳秒），这样如果目标线程在这段时间到期时还没有结束的话，
    join()方法总是能够返回。
    
    对join()方法的调用可以被中断，做法是在调用线程上调用interrupt()方法，这时需要用到try-catch子句。
   
   join()必须在线程start方法之后调用才有意义，如果一个线程都没有start，那它就无法同步了。
   join()方法是通过调用线程的wait方法来达到同步的目的的。例如，A线程中调用了B线程的join方法，则相当于A线程
   调用了B线程的wait方法，在调用了B线程的wait方法后，A线程就会进入阻塞状态。当B线程执行完或者达到等待时间，
   B线程就会调用自身的notifyAll方法唤醒A线程，从而达到同步的目的。


  **10.捕获异常：UncaughtExceptionHandler接口**
       线程中的异常会逃出异常，使用普通的try catch方式无法捕获异常。

       Thread.UncaughtExceptionHandler是Java SE5中的接口，它允许你在每一个Thread对象上
       都附着上一个异常处理器。Thread.UncaughtExceptionHandler.uncaughtException()会在线程
       因未捕获的异常而临近死亡的时候被调用。
       
       实例：CaptureUncaughtException.java
    
       如果所有线程中抛出的异常，使用的相同的异常处理器，可以在Thread类中设置一个静态域，并将这个处理器设置为
       默认的未捕获异常处理器。
       
       public class SettingDefaultHandler {
       
           public static void main(String[] args){
               Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
               ExecutorService exec = Executors.newCachedThreadPool();
               exec.execute(new ExceptionThread());
               //设置ExceptionThread类的默认异常处理器为MyUncaughtExceptionHandler
           }
       }
       
       
       这个默认异常处理器只有在不存在线程专有的未捕获异常处理器的情况下才会被调用。
       系统会检查线程专有版本，如果没有发现，则检查线程组是否有其专有的uncaughtException()方法，
       如果也没有，再调用defaultUncaughtExceptionHandler。
       
     如果一个线程即设置了自己的异常处理器，Thread类也设置了默认的异常处理器，则以线程自己的为准

  11.共享资源
        
        通过将线程要执行的任务与线程需要访问的共享资源进行分离，这可以消除所谓竞争条件，即两个或者更多的任务
        竞争响应某个条件，因此产生冲突或者不一致结果的情况。
        需要仔细考虑并防范并发系统失败的所有可能途径，例如：
        一个任务不能依赖于另一个任务，因为任务关闭的顺序无法得到保证。这里，通过使用任务以来与非任务对象，
        我们可以消除潜在的竞争条件。    
    
        基本上所有的并发模式在解决线程冲突问题的时候，都是采用序列化访问共享资源的方案，
        这意味着给定时刻只允许一个任务访问共享资源。
        
        共享资源一般是以对象形式存在的内存片段，但是可以使文件、输入/输出端口，或者是打印机。
        要控制对共享资源的访问，需要先将其包装进一个对象，然后将所有要访问这个资源的方法标记为synchronized，
        这样对该共享资源的访问就可以使用锁来控制。
        
        注意：被锁住的共享资源域变量需要设置为private。
        
        一个任务对同一个资源锁住多次----可重入锁：
            一个任务可以多次获得对象的额锁，如果方法m1在加锁对象Object上调用了m2，m2同样又调用了该加锁对象上的m3方法
            会导致对同一个对象加多次锁。
    
            JVM负责跟踪对象被加锁的次数，如果一个对象被解锁，即锁别完全释放，其计数变为0；在任务第一次给对象加锁的时候，
            计数变为1；每当这个相同的任务在这个对象上获得锁时，技术都会递增。只有首先获得了锁的任务才能允许继续获得多个锁。
            每当任务离开一个synchronized方法，计数递减，当计数为零的时候，锁被完全释放，此时别的任务就可以使用此资源。


  12.使用显示的Lock对象
     使用显式Lock对象与使用synchronized关键字的区别：
     1.使用synchronized关键字时，如果某些事物失败了，就会抛出一个异常，但是你没有机会去做任何清理工作，
        以维护系统使其处于良好状态。
        使用显式的Lock对象，可以使用finally子句将系统维护在正确的状态。
      
        public int next() {
        lock.lock();
        try{
           ++currentEventValue;
           Thread.yield();
           ++currentEventValue;
           //注意 return语句必须在try子句中出现，以确保unlock()不会过早发生，从而将数据暴露给第二个任务
           return currentEventValue;
        }finally {
            lock.unlock();
        }
      
  2.一般只有在解决特殊问题时，才会使用显式的Lock对象。例如，使用sychronized关键字不能实现，尝试着获取锁，
      并且最终获取锁失败。或者尝试着获取锁一段时间，如果没有获取到则放弃它。
      
      ReentrantLock允许你尝试着获取但是最终未获取到锁，这样如果其他人已经获取到了这个锁，你就可以决定离开去执行一些其他事情，
      而不是直至这个锁被释放。
      
      示例：AttemptLocking.java
    
      3.显式的Lock对象在加锁和释放锁方面，相对于内建的synchronized锁来说，还赋予了你更细粒度的控制力。
      
      //todo 了解ReentrantLock
      
      
  13.原子性与可视性
      原子性
       
        原子操作是不能被线程调度机制中断的操作，一旦操作开始，那么它一定可以在线程切换之前完成。
        
        原子性可以应用于除long和double之外的所有基本类型上的简单操作，JVM可以将这些操作当做原子性的操作来操作内存。
        但是JVM可以将64位（long和double）的读取和写入当做两个分离的32位操作来执行，这就产生了读取和写入操作中间
        发生上下文切换，从而会导致不同的任务可以看到不正确结果。
        当你定义long或者double变量时，如果使用volatile关键字修饰变量，则变量的简单赋值与读取操作就具有了原子性。
        
   可视性---volatile关键字
        
        一个任务作出的修改，即使在不中断的意义上讲是原子性的，对其他任务也可能是不可视的，例如，修改只是暂时性地存储
        在本地处理器的缓存中，因此不同的任务对应用的状态有不同的视图。
    
        【**volatile关键字确保了应用中的可视性，如果你将一个域声明为volatile的，那么只要是对这个域产生了写操作，那么所有的
        读操作就可看到这个修改。即使使用了本地缓存，volatile修饰的域就会被立即写入到主存中，而读取操作就发生在主存中。**】
    
        在非volatile域上的原子操作不必刷新到主存中去，其他读取该域的任务也不必看到这个新值。
        1.如果多个任务在同时访问某个域，那么这个域就应该是volatile的，否则这个域就应该只能经由同步来访问。因此，如果一个域
        完全由synchronized方法或语句块来防护，那就不必将其设置为是volatile的。
        2.一个任务所作的任何写入操作对这个任务来说都是可视的，因此如果它只是需要在这个任务内部可视，那么你就不需要将其设置为volatile。
        3.当一个域的值依赖于它之前的值时，volatile就无法工作了；
        4.如果某个域的值受到其他域的值的限制，那么volatile也无法工作，例如Range类的lower和upper边界，就必须遵循lower<=upper的限制。
        5.使用volatile而不是synchronized的唯一安全的情况是类中只有一个可变的域。


        volatile再析：
            线程为了提高效率，将某成员变量A拷贝了一份B，线程对成员变量A的访问其实是访问B，只在某些动作时才进行A和B的同步。
            因此存在A和B不一致的情况。在Java内存模型中，有主存和线程的本地缓存，为了性能，一个线程会在自己的本地缓存中保存
            要访问的变量的副本。这样就会出现同一个变量在某个瞬间，一个线程本地缓存中的值与另一个线程本地缓存中的值或主存中的值
            不一致的情况。
            使用volatile修饰的变量，线程在每次使用变量的时候，都会要求线程从主存中读出其最新的值。在每次线程修改便利总之后，
            会要求线程立即将最新的值写回主存中,因此volatile修饰的变量的存取比一般的变量消耗的资源要更多一点。

         volatile  vs synchronized
            synchronized:首先，synchronized获得并释放监视器——如果两个线程使用了同一个对象锁，监视器能强制保证代码块同时只被一个线程所执行。
            但是，synchronized也同步内存：事实上，synchronized在"主"内存区域同步整个线程的内存。

            1. 线程请求获得监视的对象锁（假设未被锁，否则线程等待直到锁释放） 
            2. 线程内存的数据被消除，从“主”内存区域中读入
            3. 代码块被执行 
            4. 对于变量的任何改变现在可以安全地写到“主”内存区域中
            5. 线程释放监视的对象锁

            因此volatile只是在线程内存和"主"内存间同步某个变量的值，synchronized关键字通过锁定和解锁某个监视器同步所有变量的值。

  14.原子类
        Java SE5中引入了AtomicInteger AtomicLong AtomicReference等特殊的原子性变量类，
        Atomic类被设计用来构建java.util.concurrent中的类，因此只有在特殊情况下才在自己的代码中使用它们，即便是使用了也需要确保不存在其他
        可能出现的问题。通常依赖于锁要更安全一些（要么是synchronized关键字，要么是显式的Lock对象）。
        
  15.临界区
        
        使用synchronized关键字来建立临界区，synchronized被用来指定某个对象，此对象的锁被用来对花括号内部的代码进行同步控制。
        synchronized(syncObject){
        
        }
        进入同步代码块之前，需要得到syncObject对象的锁，如果其他线程已经得到这个锁，就得等到锁被释放以后，才能进入临界区。     
      
        【Atomic类被设计用来构建java.util.concurrent中的类，因此只有在特殊情况下才在自己的
        代码中使用它们，即便使用了也需要确保不存在其他可能出现的问题。通常依赖于锁要更安全一些，
        要么是synchronized关键字，要么是显式的Lock对象。】    

        有时，只是希望多个线程同时访问方法内部的部分代码而不是防止访问整个方法，通过这种方式分离出来的代码
        称为临界区。使用同步代码块而不是同步整个方法可以使得资源被锁定的时间更短，其他线程更多的访问。
    
        synchronized(syncObject){
    
        }
    
   在其他对象上同步
        synchronized块必须给定一个在其上进行同步的对象，如果使用synchronized(this),如果获得了synchronized块上的
    锁，那么该对象其他的synchronized方法和临界区就不能被调用了。如果使用synchronized(this)进行同步，则必须确保多个
    线程访问的是同一个对象中的同步方法块。
        有时必须在另一个对象上同步，这时必须确保所有相关的线程任务都是在同一个对象上同步的。这时，两个任务可以进入同一个对象
        只要这个对象上的方法是在不同的锁上同步的即可。
        使用synchronized修饰方法，锁住的是当前类对象
    
    
  16.线程本地存储 ThreadLocal
  
    防止任务在共享资源上产生冲突除了使用synchronized同步锁机制外，还可以使用线程本地存储根除对变量的共享.线程本地存储可以为使用
    相同变量的每个不同线程创建不同的存储。因此，如果你有5个线程都要使用某个变量所表示的对象，那线程本地存储就会
    生成5个用于存储该变量的不同的存储块。
    
    创建和管理线程本地存储可以由java.lang.ThreadLocal类来实现。
    当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变
    自己的副本，而不会影响其他线程所对应的副本。
    
    
    ThreadLocal对象通常当做静态域存储，在创建ThreadLocal时，只能通过get()和set()来访问该对象的内容。
    get()----返回与其线程相关联的对象的副本；
    set()----将参数插入到其线程存储的对象中，并返回存储中原有的对象。
    remove()----将当前线程局部变量的值删除，目的是为了减少内存的占用。 
                当线程结束之后，对应该线程的局部变量将会自动被垃圾回收
                所以显式调用该方法清除线程的局部变量并不是必须的操作，但是它可以加快内存的回收速度。
    initialValue()----返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的，这个方法
                        是一个延迟调用的方法。当线程第一次调用get()或set()才会执行，并且只执行一次。
                        ThreadLocal中的缺省实现直接返回一个null。
                        
  ThreadLocal为每一个线程维护变量副本的原理：
       在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，值为对应线程的变量副本。
       
       eg:ThreadLocal的set方法
       public void set(T value) {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
    }
    
    首先通过getMap(thread t)方法获取一个和当前线程相关的ThreadLocalMap，然后将变量的值设置到这个ThreadLocalMap对象中，
    当然如果获取到的ThreadLocalMap对象为空，就通过createMap方法创建。    
    
    ThreadLocalMap是ThreadLocal类的一个静态内部类，它实现了键值对的设置和获取，每个线程都有一个独立的ThreadLocalMap副本，
    它所存储的值，只能被当前线程读取和修改。ThreadLocal类通过操作每一个线程特有的ThreadLocalMap副本，
    从而实现了变量访问在不同线程中的隔离。因为每个线程的变量都是自己特有的，完全不会有并发错误。
    还有一点就是，ThreadLocalMap存储的键值对中的键是this对象指向的ThreadLocal对象，而值就是你所设置的对象了。
    我们可以创建不同的ThreadLocal实例来实现多个变量在不同线程间的访问隔离，因为不同的ThreadLocal对象作为不同键，
    当然也可以在线程的ThreadLocalMap对象中设置不同的值了。
  
  
  ThreadLocal与线程同步机制的比较
        ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
        
        线程同步机制：通过对象的锁机制保证同一时间只有一个线程访问变量，这时该变量是多个线程共享的，
        使用同步机制要求程序慎密地分析什么时候对变量进行读写，什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，
        程序设计和编写难度相对较大。
    
        
        ThreadLocal:从另一个角度来解决多线程的并发访问，ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据
        的访问冲突。因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，
        在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
    
        对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，而ThreadLocal采用了“以空间换时间”的方式。
        前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
    
        在Spring中，绝大部分Bean都可以声明为singleton作用域，就是因为Spring对一些Bean采用了ThreadLocal进行处理，
        让他们也成为线程安全的状态。
        
        一般的Web应用划分为视图层、服务层和持久层三个层次，在不同的层中编写对应的逻辑，下层通过接口向上层开放功能调用。
        在一般情况下，从接收请求到返回响应所经过的所有程序调用都属于同一个线程。同一线程贯穿三层，这样就可以根据需要将一些
        非线程安全的变量以ThreadLocal存放，在同一次请求响应的调用线程中，所有关联的对象引用到的都是同一变量。
        https://www.cnblogs.com/ldq2016/p/9041856.html


  17.线程状态：
  一个线程可以处于以下四种状态之一：
  1)新建：当线程被创建时，它只会短暂的处于这种状态，此时它已经分配了必须的系统资源，并执行了初始化。
         此刻线程已经有资格获得CPU时间了，之后调度器将把这个线程转变为可运行状态或者阻塞状态。

  2)就绪：在就绪状态下，只要调度器将时间片分给线程，线程就可以运行。

  3)阻塞：线程能够运行，但是有某个条件阻止它的运行，当线程处于阻塞状态时，调度器将忽略线程，
        不会分配给线程任何CPU时间，直到线程重新进入了就绪状态，它才有可能执行任务。

  4)死亡：处于死亡或者终止状态的线程将不再是可调度的，并且再也不会得到CPU时间，它的任务已经结束，
            或者不再是可运行的。  任务死亡的通常方式是从run()方法返回，但是任务的线程还可以被中断，
            你将会看到这一点。


      任务进入阻塞状态有可能有以下原因：
      1)通过调用sleep(milliseconds)使任务进入休眠状态，在这种情况下，任务在指定的时间内不再运行。

      2)调用wait()使得线程挂起，直到线程得到了notify()或者notifyAll(),线程才会进入就绪状态。

      3)任务在等待某个输入/输出完成；

      4)任务试图在某个对象上调用其同步控制方法，但是对象锁不可用，因为另一个任务已经获取到了这个锁。


      有时希望能够终止处于阻塞状态的任务，可以选择让其达到代码中可以检查其状态值得某一个点，让其主动地终止；
      或者强制让这个任务跳出阻塞状态。

  中断
    Thread类包含interrupt()方法，因此你可以中止被阻塞的任务，这个方法将设置线程的中断状态。
    如果一个线程已经被阻塞，或者试图执行一个阻塞操作，那么设置这个线程的中断状态将会抛出InterruptedException。
    当抛出该异常或者 该任务调用Thread.interrupted()时，中断状态将会被复位。??

    在Executor上调用shutdownNow()，那么它将会发送一个interrupt()调用给它启动的所有线程，关闭某个特定Executor的所有任务。


    你能够中断对sleep()的调用，但是不能中断正在试图获取synchronized锁或者试图执行I/O操作的线程，
    这意味I/O具有锁住多线程程序的潜在可能。
    有一个略显笨拙但是有时确实行之有效的解决方案，即关闭任务在发生阻塞的底层资源：

    
被互斥所阻塞
    只要任务以不可中断的方式被阻塞，那么都有潜在的会锁住程序的可能。使用ReentrantLock，
    【**在ReentrantLock上阻塞的任务具备可以被中断的能力**】。
    示例：Interrupting2.java


检查中断
    当在线程上调用interrupt()时，中断发生的时刻是run()内部已经发生了阻塞或者将要进入到阻塞操作中，这时可以通过在
    阻塞上抛出异常来跳出阻塞状态。
    如果run()内部没有产生任何阻塞，又想要跳出run()。

    可以通过调用Thread.interrupted()方法来检查线程当前是否处于中断状态。

线程之间的协作
    在一个流程中，某些任务可以并行执行，但是某些步骤需要所有的任务都结束之后才能开动。

   1.wait()------等待某个条件发生变化，通常这个条件将由另一个任务来改变。
   
    wait()会在等待外部世界产生变化时将任务挂起，只有在notify()或者notifyAll()发生时，这个任务才会被唤醒并去
    检查所发生的变化。wait()提供了一种在任务之间对活动同步的方式。
    
   【调用sleep()和yield()的时候锁并没有被释放，调用wait()线程的执行将会被挂起，对象上的锁被释放。】
    
    wait()将会释放锁，这就意味着另一个任务可以获得这个锁，因此这就意味着另一个任务可以获得这个锁，因此这个对象
    的其他synchronized方法可以在wait()期间被调用，这些其他的方法通常将会发生改变，而这种变化正是被挂起的任务
    重新唤醒所需要的条件。例如：浇筑混凝土任务在等待，如果钢筋铺建好了请用notify或者notifyAll()叫醒它。

    两个版本：
    1.wait(long millseconds)
   
    (1)在wait()期间对象锁是释放的；
    （2）可以通过notify() notifyAll，或者时间到期，从wait()中恢复执行。
    
    2.wait()
   
        无限期等待，直到线程接收到notify() notifyAll() 被唤醒。

    wait() notify() notifyAll()方法是Object类中的方法。
    只能在同步控制方法或者同步控制块里面调用wait()  notify()和notifyAll()，如果在非同步控制方法里面调用这些方法，
    程序能够通过编译，但是运行的时候，将会抛出IllegalMonitorStateException异常，因为调用这些方法之前必须获取对象的锁。

    使用wait()完成奇数偶数交替打印

    【**需要使用一个检查某个条件的while循环包围wait()**】，因为：

    1.你可能有多个任务出于相同的原因在等待同一个锁，而第一个被唤醒的任务可能会改变这种状况。
    如果属于这种情况，那么这个任务应该被再次挂起，直至其感兴趣的条件发生变化。

    2.在你的任务从其wait()中被唤醒的时刻，有可能会有某个其他的任务已经做出了你的任务想要做出的改变。
    从而使得这个任务此时不能执行，或者执行此操作已经无关紧要。(比如，你想要等面包做好了吃面包，但是等面包做好了的那一刻，
    你发现面包已经被哪个兔崽子给吃了；你要等待洗衣机没人用了，洗你的衣服，但是当你发现终于没人用的时候，你老妈已经抢先一步洗了你想要洗的衣服。。。。)
    此时应该再次调用wait()将你的线程重新挂起。
    
    3.也有可能某些任务调用notifyAll()唤醒了你，但是这并不是你想要的条件，因此你需要检查一下是否是正确的原因，
     如果不是，就再次调用wait()。

  2.notify() vs notifyAll()
  
    使用notify()时，在众多等待同一个锁的任务中只有一个会被唤醒。
    【调用notifyAll()并不是唤醒所有正在等待的任务，而是 当notifyAll()因为某个特定的锁而被调用时，只有等待
         这个锁的全部任务才会被唤醒，如果等待的不是当前被释放的锁，则线程不会被唤醒。】
        示例见NotifyVsNotifyAll.java
        
     在复杂的情况下，可能会有多个任务在某个特定的对象锁上等待，因此你不知道哪个任务应该被唤醒。因此，调用notifyAll()
     要更安全一些，这样可以唤醒等待这个锁的所有任务，每个任务都必须决定这个通知是否与自己相关。
  
  3. 生产者与消费者
        
        一个饭店，它有一个厨师和一个服务员。这个服务员需要等待厨师准备好膳食，当厨师准备好时，他会通知服务员，之后服务员上菜，
        然后返回继续等待。
        生产者：厨师，
        消费者：服务员
        两个任务必须在膳食被生产和消费时进行握手，系统必须以有序的方式关闭。
  
        示例:Restaurant.java
        要点:1.shutownNow()将向所有由ExecutorService启动的任务发送interrupt()，但是在Chef中，任务并没有在
        获得该interrupt之后立即关闭，因为当任务试图进入一个可中断的阻塞操作时，这个中断只能跑出InterruptedException。
        因此，在示例中你将看到在调用shutdownNow()之后，程序没有立即关闭执行，而是继续输出了“Order Up!”，直到程序想要
        进入可中断的sleep()操作时，抛出了InterruptedException。
        
        使用两个锁，自己对象的锁用来判断是否符合条件等待，如果已经有了自己想要的条件发生，则从wait中跳出，然后
        获取对方的锁：用来执行自己应该做的任务（因为获取了对方的锁，对方现在是无法执行任务的），然后叫醒对方
        
使用显式地Lock和Condition对象
    1.挂起：使用Condition类上的await()来挂起一个任务
    2.唤醒：当外部条件发生变化,某个任务可以继续执行时，可以通过调用signal()唤醒一个任务。
            或者使用signalAll()来唤醒所有在这个Condition上被其自身挂起的任务。
            
    每个对lock()的调用都必须紧跟一个try-finally子句，用来保证所有情况下都可以释放锁。
    在使用await() signal或者signalAll()之前必须拥有这个锁。
    
    
生产者-消费者队列
    wait() notifyAll()以一种非常低级的方式解决了任务互操作问题，即每次交互都握手。
    使用同步队列好像可以完美解决任务协作问题：
    同步队列在任何时刻都只允许一个任务插入或者移除元素。如果消费者任务试图从队列中获取对象，
    而该队列此时为空，那么这些队列还可以挂起消费者任务，并且当有更多的元素时可以恢复消费者任务。
    
    阻塞队列可以解决非常大量的问题，与wait() notifyAll()相比,使用同步队列简单可靠的多。
    
    
    接口java.util.concurrent.BlockingQueue：
    LinkedBlockingQueue:是一个无界队列
    ArrayBlockingQueue:具有固定的尺寸，可以在它被阻塞之前，向其中放置有限数量的元素。
  
  
线程间使用管道进行通信
        线程间通过输入输出管道在线程间进行通信
        PipeWriter:允许任务向管道写；
        PipeReader:允许不同任务从同一个管道中读取
        PipedReader的建立必须在构造器中与一个PipedWriter相关联，PipeReader与普通IO之间最重要的差异是PipeReader是可中断的。
        管道基本上是一个阻塞队列，当线程从管道中读取数据时，如果没有更多的数据，管道将会自动阻塞,不需要调用任何wait()方法。


死锁
    发生死锁的条件：
    1.互斥条件：任务使用的资源中至少有一个是不能共享的；
    2.至少有一个任务它必须持有一个资源且正在等待获取另一个当前被别的任务持有的资源；
    3.资源不能被任务抢占，任务必须将资源释放当做普通事件，也就是说任务不会从别的任务那里抢资源
    4.必须有循环等待，一个任务呀等待其他任务所持有的资源，后者又在等待另一个任务所持有的资源，这样一直下去，直到有一个任务
       在等待第一个任务所持有的资源，使得大家都被锁住。

    防止死锁只需要破坏其中一个条件即可，最容易破坏的是第4个。


java.util.concurrent中的构件

1.CountDownLatch

    CountDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行；
    这是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕之后，计数器的值就-1，当计数器的值为0，
    当计数器的值为0时，表示所有线程都执行完毕，然后再闭锁上等待的线程就可以恢复工作了。
    可以向CountDownLatch对象设置一个厨师计数值，任何在这个对象上调用wait()的方法都将会阻塞，直至这个计数值到达0。


    构造器：

    1.public CountDownLatch(int count){}-----count为计数值
    2.await()-----调用await()的线程将会被挂起，直到count值为0才会继续执行；
    3.await(long timeout, TimeUnit unit)-----和await()类似，只不过在等待一定时间后count值还没有变为0的话就会继续执行；
    4.countDown()-----将count值减1
    5.getCount()------获取当前计数值


2.CyclicBarrier---简单翻译过来是循环屏障
    适用情景：你希望创建一组任务，它们并行地执行工作，然后在进行下一个步骤之前等待，知道所有的工作完成。
    使得所有的并行任务都将在-栅栏处列队，因此可以一致性地向前移动。
    CyclicBarrier与CountDownLatch的区别：
    1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次；
    2.CyclicBarrier：的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用。

    例子：
       就像生活中我们约朋友们到某个餐厅一起吃饭，有些朋友可能会早到，有些朋友可能会晚到，
       但是这个餐厅规定必须等到所有人到齐之后才会放我们进去。这里的朋友们就是各个线程，
       餐厅就是CyclicBarrier

    构造方法：
        1.public CyclicBarrier(int parties);
        2.public CyclicBarrier(int parties, Runanble barrierAction)

        parties------参与线程的个数；
        Runnable参数----指定当最后一个到达线程要做到的任务

    重要方法
    1.public int await() throws InterruptedException,BrokenBarrierException
    2.public int await(long timeout,TimeUnit unit) throws InterruptedException,BrokenBarrierException,TimeoutException

    解析：
    1.线程调用await()表示自己已经达到栅栏；
    2.BrokenBarrierException:表示栅栏已经被破坏，破坏的原因可能是其中一个线程await()时被中断或者超时；
  
  
DelayQueue
    1.DelayQueue是一个无界的BlockingQueue，用于放置实现了Delayed接口的对象，其中的对象只能在其到期时
    才能从队列中取走，这种队列是有序的，即队列头元素是最接近过期的元素。
    2.如果没有任何延迟到期，那么就不会有任何头元素，并且poll()将会返回null。
    3.超时判定是通过getDelay(TimeUnit.NANOSECONDS)方法的返回值小于等于0来判断。
    4.注意：不能讲null元素放置到这种队列中。
    5.延迟队列实现了Iterator接口，但是iterator()遍历顺序不保证是元素的实际存放顺序
    
    
  队列元素
    DelayQueue<E extends Delayed>的队列元素需要实现Delayed接口：
    
    public interface Delayed extends Comparable<Delayed>{
        long getDelay(TimeUnit unit);
    }
    
    队列元素要实现getDelay(TimeUnit unit)方法和compareTo(Delayed o)方法，
    getDelay()定义了剩余到期时间，
    compareTo()方法定义了元素排序规则-----元素的排序规则影响了元素的获取顺序，将在后面说明。
    
    内部存储结构
    DelayQueue的元素存储交由优先级队列存放
    
    public class DelayQueue<E extends Delayed> extends AbstractQueue<E> implements BlockingQueue<E> {
        private final transient ReentrantLock lock = new ReentrantLock();
        private final PriorityQueue<E> q = new PriorityQueue<E>();//元素存放
        
    DelayQueue的优先级队列使用的排序方式是队列元素的compareTo方法，优先队列存放顺序是从小到大的，
    所以队列元素的compareTo方法影响了队列的出队顺序。
    
    若是compareTo方法定义不当，会造成延时高的元素在队头，延时低的元素无法出队。
    
  
  DelayQueue中的重要方法：
    1.boolean add(E e)
    2.void clear()----从此延迟队列中原子地删除所有的元素
    3.int drainTo(Collection<? super E> c)-----从该队列中删除所有可用的元素，并将它们添加到给定的集合中
    4.Iterator<E> iterator()-----返回此队列中所有元素（包括已经过期和未过期）的迭代器
    5.boolean offer(E e)------将制定的元素插入到此延迟队列中
    6.boolean offer(E e,long timeout,TimeUnit unit)
    7.peek()----检索但不删除此队列的头，如果此队列为空，则返回null
    8.poll()----检索并删除此队列的头，或者如果次队列没有已经过期延迟的元素，则返回null
    9.poll(long timeout, TimeUnit unit)----检索并删除此队列的头部，需等待此队列具有到期延迟的元素
                                            或者等待指定的时间
    10.void put(E e)------将指定的元素插入到此延迟队列中（此插入不会有延迟，因为是无界的）；
    11.int  remainingCapacity()----总是返回Integer.MAX_VALUE,因为DelayQueue没有容量限制；
    12.boolean remove(Object o)-----从该队列中删除指定元素的单个实例（如果存在）无论其是否已经过期
    13.size()----返回此集合中的元素数；
    14.E take()------检索并删除此队列的头部，如果需要，等待一个延迟到期的元素在此队列上可用
    15.Object[] toArray()------返回一个包含此队列中所有元素的数组
    16.<T> T[]  toArray(T[] a)------返回一个包含此队列中所有元素的数组，返回的数组的运行时类型是指定数组的运行时类型
    

线程的Leader-Follower模式
    在Leader-Follower线程模型中每个线程有三种状态：领导leader 追随follower 处理processing
    假设共N个线程，其中只有1个leader线程（等待任务），X个processing线程（处理）余下有N-1-x个follower线程，
    有一把锁，谁抢到谁就是leader,它的任务是等待新任务；
    时间/任务来到时，leader线程会对其进行处理，从而转为processing状态，处理完成之后又转变为following;
    丢失leading后，follower会尝试抢占锁，抢到则变为leader，否则保持follower
    follower不干事，就是抢占锁，力图称为leader

    在延迟队列DelayQueue中可以看到这种线程模式，这种队列常见用于以下问题：
    （1）关闭空闲连接，服务中有很多客户端的连接，空闲一段时间之后需要将其关闭；
    （2）缓存，缓存中的对象，超过了空闲时间，需要从缓存中移出



sleep和wait有什么区别：
    (1)这两个方法来自不同的类分别是Thread和Object：wait是Object对象中的实例方法，sleep是Thread类中的静态方法
    (2)sleep()方法不会释放锁，wait方法释放了锁；
    (3)wait  notify 和notifyAll只能在同步控制方法或者同步控制块中使用，而sleep可以在任何地方使用；
    (4)wait()使用notify或者notifyAll()唤醒；sleep()通过超时或者调用iterrupt()方法唤醒

线程的状态：new  runnable waiting timedwaiting blocked  dead

    1.new--->runnable: start()
    2.runnable--->waiting: object.wait()或者调用thread.join()
      waiting---->runnable: notify()或者notifyAll()唤醒wait()方法，或者当调用thread.join()时，等待的线程thread的run()执行完毕
    3.runnable---->timedwaiting: sleep(long time)
      timedwaiting---->runnable:等待时间结束
    4.runnable----->blocked：等待同步锁释放
      blocked------>runnable:同步锁释放，获取锁
    5.runnable---->dead:run()执行完毕



PriorityBlockingQueue

    1.PriorityBlockingQueue是一个支持优先级的无界有序的阻塞队列，直到系统资源耗尽。该队列不支持插入null元素，同时不只是插入非comparable的对象；
    2.默认情况下元素采用自然顺序升序排列，也可以使用元素实现comparable接口中的compareTo()方法来指定元素排序顺序，或者初始化PriorityBlockingQueue时，
    指定构造参数Comparator来对元素进行排序。不保证同等优先级的元素顺序，如果想要强制顺序就要考虑自定义顺序或者使用Comparator使用第二个比较属性。
    3.它的迭代器并不保证队列保持任何特定的顺序，如果想要顺序遍历，考虑使用Arrays.sort(pq.toArary());
    
    PriorityBlockingQueue是基于最小二叉堆实现的，使用基于CAS的自旋锁来控制队列的动态扩容，保证了扩容不会则色take操作的执行。
    
    //最小二叉堆
    //CAS自旋锁











