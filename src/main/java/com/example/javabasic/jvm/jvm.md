volatile关键字再析：
    https://mp.weixin.qq.com/s/DZkGRTan2qSzJoDAx7QJag
    要看《深入理解Java虚拟机》


    JVM内存模型
    Java内存模型简称JVM(Java Memory Model)是虚拟机所定义的一种抽象规范，用来屏蔽不同硬件和操作系统的内存访问差异，
    让Java程序在各种平台下都能达到一致的内存访问结果。

    1.主内存：
    主内存可以简单理解为计算机当中的内存，但是又不完全相同。主内存被所有的线程共享，对于一个共享变量（比如静态变量，
    或者堆内存中的实例）来说，主内存当中存储了它的“本尊”，工作内存中存储的只是其备份。

    2.工作内存
    工作内存可以简单理解为计算机当中的CPU高速缓存，但是又不完全等同。
    每一个线程拥有自己的工作内存，对于一个共享变量来说，工作内存当中存储了它的副本。
    线程对共享变量的所有操作都必须在工作内存内进行，不能直接写入主内存中的变量。
    不同线程之间也无法访问彼此的工作内存，变量值的传递只能通过主内存来进行。

    直接操作主内存太慢了，所以JVM才不得不利用性能较高的工作内存，主内存与工作内存的关系有点像计算机的内存与工作缓存之间的关系。


    volatile关键字具有许多特性，其中最重要的特性就是保证了用volatile修饰的变量对所有线程的可见性。

    这里的可见性是什么意思呢？当一个线程修改了变量的值，新的值会立刻同步到主内存当中。而其他线程读取这个变量的时候，也会从主内存中拉取最新的变量值。

    为什么volatile关键字可以有这样的特性？这得益于java语言的先行发生原则（happens-before）。
    先行发生原则在维基百科上的定义如下：

    在计算机科学中，先行发生原则是两个事件的结果之间的关系。如果一个时间发生在另一个事件之前，结果必须反映，即使这些事件实际上是乱序执行的。
    这里所谓的事件，实际上就是各种指令操作，比如读操作、写操作、初始化操作、锁操作等等。

    先行发生原则作用于很多场景下，包括同步锁、线程启动、线程终止、volatile。我们这里只列举出volatile相关的规则：
    【**对于一个volatile变量的写操作先行发生于后面对于这个变量的读操作。**】

    eg:
    如果在静态变量s之前加上volatile修饰符：
    volatile static int s = 0；
    线程A执行如下代码：
    s = 3；
    这时候我们引入线程B，执行如下代码：
    System.out.println("s=" + s);
    当线程A先执行的时候，把s = 3写入主内存的事件必定会先于读取s的事件。所以线程B的输出一定是s = 3。


    值得注意的是，volatile只能保证变量的可见性，不能保证变量的原子性。
    因此，什么时候适合用volatile呢？

    1.能够确保只有单一的线程修改变量的值。

    2.变量不需要与其他的状态变量共同参与不变约束。
     第一条很好理解，就是上面的代码例子。第二条是什么意思呢？可以看看下面这个场景：

        volatile static int start = 3;
        volatile static int end = 6;

        线程A执行如下代码：
            while (start < end){
                //do something
            }

        线程B执行如下代码：
        start+=3;
        end+=3;

        这种情况下，一旦在线程A的循环中线程B执行了，start有可能先更新成6，造成了一瞬间 start == end，从而跳出while循环的可能性。


     volatile如何进行指令重排：

 指令重排：

      指令重排是指JVM在编译Java代码的时候，或者CPU在执行JVM字节码的时候，对现有的指令顺序进行重新排序。
      指令重排的目的是为了在不改变程序执行结果的前提下，优化程序的运行效率。需要注意的是，这里所说的不改变指定结果，
      指的是不改变单线程下的程序执行结果。

      然而，指令重排是一把双刃剑，虽然优化了程序的执行效率，但是在某些情况下，会影响到多线程的执行结果。
      我们来看看下面的例子：

      boolean contextReady = false;

      在线程A中执行:
      context = loadContext();
      contextReady = true;

      在线程B中执行:
      while( ! contextReady ){
         sleep(200);
      }
      doAfterContextReady (context);


      以上程序看似没有问题。线程B循环等待上下文context的加载，一旦context加载完成，contextReady == true的时候，才执行doAfterContextReady 方法。

      但是，如果线程A执行的代码发生了指令重排，初始化和contextReady的赋值交换了顺序：


      boolean contextReady = false;

      在线程A中执行:
      contextReady = true;
      context = loadContext();

      在线程B中执行:
      while( ! contextReady ){
         sleep(200);
      }
      doAfterContextReady (context);


      这个时候，很可能context对象还没有加载完成，变量contextReady 已经为true，线程B直接跳出了循环等待，开始执行doAfterContextReady 方法，结果自然会出现错误。

      需要注意的是，这里java代码的重排只是为了简单示意，真正的指令重排是在字节码指令的层面。


    什么是内存屏障？

      内存屏障（Memory Barrier）是一种CPU指令，维基百科给出了如下定义：

      A memory barrier, also known as a membar, memory fence or fence instruction, is a type of barrier instruction that causes a CPU or compiler to enforce an ordering constraint on memory operations issued before and after the barrier instruction. This typically means that operations issued prior to the barrier are guaranteed to be performed before operations issued after the barrier.

      翻译结果如下：

      内存屏障也称为内存栅栏或栅栏指令，是一种屏障指令，它使CPU或编译器对屏障指令之前和之后发出的内存操作执行一个排序约束。 这通常意味着在屏障之前发布的操作被保证在屏障之后发布的操作之前执行。

      内存屏障共分为四种类型：

      LoadLoad屏障：
      抽象场景：Load1; LoadLoad; Load2
      Load1 和 Load2 代表两条读取指令。在Load2要读取的数据被访问前，保证Load1要读取的数据被读取完毕。

      StoreStore屏障：
      抽象场景：Store1; StoreStore; Store2
      Store1 和 Store2代表两条写入指令。在Store2写入执行前，保证Store1的写入操作对其它处理器可见

      LoadStore屏障：
      抽象场景：Load1; LoadStore; Store2
      在Store2被写入前，保证Load1要读取的数据被读取完毕。

      StoreLoad屏障：
      抽象场景：Store1; StoreLoad; Load2
      在Load2读取操作执行前，保证Store1的写入对所有处理器可见。StoreLoad屏障的开销是四种屏障中最大的。


    volatile做了什么？

    在一个变量被volatile修饰后，JVM会为我们做两件事：

    1.在每个volatile写操作前插入StoreStore屏障，在写操作后插入StoreLoad屏障。
    2.在每个volatile读操作前插入LoadLoad屏障，在读操作后插入LoadStore屏障。

    或许这样说有些抽象，我们看一看刚才线程A代码的例子：

    boolean contextReady = false;

    在线程A中执行:
    context = loadContext();
    contextReady = true;

    我们给contextReady 增加volatile修饰符，会带来什么效果呢？

    volatile boolean contextReady = false;

    在线程A中执行:
    context = loadContext();
    StoreStore屏障
    contextReady = true;
    StoreLoad屏障

    由于加入了StoreStore屏障，屏障上方的普通写入语句 context = loadContext()
    和屏障下方的volatile写入语句 contextReady = true 无法交换顺序，从而成功阻止了指令重排序。


    happens-before是JSR-133规范之一，内存屏障是CPU指令，happens-before是最终目的，内存屏障是实现手段。


      几点补充：

      1. 关于volatile的介绍，本文很多内容来自《深入理解Java虚拟机》这本书。有兴趣的同学可以去看看。
      2. 在使用volatile引入内存屏障的时候，普通读、普通写、volatile读、volatile写会排列组合出许多不同的场景。我们这里只简单列出了其中一种，有兴趣的同学可以查资料进一步学习其他阻止指令重排的场景。
      3.volatile除了保证可见性和阻止指令重排，还解决了long类型和double类型数据的8字节赋值问题。这个特性相对简单，本文就不详细描述了。