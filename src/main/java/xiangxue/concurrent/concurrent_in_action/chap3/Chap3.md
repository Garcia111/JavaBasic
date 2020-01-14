并发编程实战第三章——————共享对象

3.1可见性

   3.1.2非原子的64位操作
    Java存储模型要求获取和存储操作都为原子的，但是对于非volatile的long和double变量，JVM允许将64位的读或写操作
    划分为两个32位的操作。如果读和写发生在不同的线程，这种情况读取一个非volatile类型long就可能会出现一个值的高
    32位和另一个值的低32位。---因此long和double类型的数据读取到的可能不仅仅是一个过期的值，而有可能是一个错误的值。


   3.1.3锁和可见性
       内置锁可以用来确保一个线程以某种可以预见的方式看到另一个线程影响：
       线程A持有一把锁，执行一个同步块，线程B同样请求该锁，并且在线程B的同步块中会用到线程A同步块内的变量，
       当线程A执行一个同步块时，线程B也随后进入了被同一个锁监视的同步块中，这时可以保证，在锁释放之前，对线程A可见的变量的值，B获得锁之后同样是可见的。
       换句话说，当B执行到与A相同的锁监视的同步块时，A在同步块中或之前所做的每件事，对线程B都是可见的，
       也就是**保证了锁控制的同步块内变量对持有同一个锁的两个线程的可见性**。如果没有同步，就没有这样的保证。

   **新认识**：
   当访问一个共享的可变变量时，为什么要求所有线程由同一个锁进行同步，现在可以给出另一个理由：
       为了保证一个线程对数值进行的写入，其他线程也都可见。
       另一方面，如果一个线程在没有恰当地使用锁的情况下读取了变量，那么这个变量很可能是一个过期的数据。
       锁不仅仅是关于同步与互斥的，也是关于内存可见的，为了保证所有线程都能看到共享的，可变变量的最新值，读取和写入线程
       必须使用公共的锁进行同步。


   3.1.4 Volatile变量
        1.【**volatile变量的相关操作不会重排序**】：当一个域声明为volatile类型后，编译器与运行时监视器会监视这个变量：它是共享的，并且对它的操作不会与其他的内存操作一起被重排序。
        2.volatile变量不会缓存在寄存器或者缓存在对其他处理器隐藏的地方。读一个volatile类型的变量时，总是会返回由某一个线程写入的最新值。
        3.**访问volatile变量的操作不会加锁，也就不会引起执行线程的阻塞，这使得volatile变量相对于synchronized而言，只是轻量级的同步机制**。
        4.加锁可以保证可见性与原子性，volatile变量只能保证可见性。

       正确使用volatile变量的方式包括：用于确保它们所引用的对象状态的可见性，或者用于标识重要的声明周期事件（比如初始化或关闭）的发生。
       volatile只确保变量的可见性，不确保同步。当同步策略复杂时，就不要使用volatile变量。

       volatile变量固然很方便，但是也存在限制，它们通常被当做标识完成、中断、状态的标记使用，判断volatile变量是否符合某个条件，然后执行
       某个操作。尽管volatile也可以用来标示其他类型的状态信息，但是决定这样做之前请格外小心，比如volatile的语义不足以使自增操作（count++）原子化，
       除非你能保证只有一个线程对变量执行写操作。


   **只有满足了下面所有的标准后，才能使用volatile变量**：
   1.写入变量时并【不依赖变量当前的值】，也就是不需要比较之后再赋值，直接赋值，只有一步操作；或者能够确保只有单一的线程修改变量的值；
   2.【变量不需要与其他的状态变量共同参与不变约束】

       例如下面的情景：
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

   3.【访问变量时，没有其他原因需要加锁】，因为volatile保证的是对多个线程访问的可见性，如果加了锁，保证每次只能被一个线程访问，就没有使用volatile
     关键字的必要了。


3.2发布和逸出
    发布：发布一个对象的意思是使该对象能够被当前范围之外的代码所使用。
        比如，将一个引用存储到其他代码可以访问的地方；在一个非私有的方法中返回这个引用；将这个引用传递到其他类的方法中。
    逸出：一个对象在尚未准备好时就将它发布，这种情况称作逸出

   发布一个对象，同样也发布了该对象所有非私有引用链中的对象 和 方法调用链中可以获得的对象。

    假设有一个类C，从它的视角而言，一个外部方法的行为不是完全由C定义的。这包括在其他类中的方法和C自身可以被覆盖的方法。
    将一个对象传递给外部方法，相当于将这个对象发布了。因为实际上，你并不知道它会激发哪些代码，也不知道外部方法是发布这个对象，
    还是只保留它的引用，以供其他线程使用。

    public class ThisEscape {

        public ThisEscape(EventSource source){
            source.registerListener(new EventListener(){

                public void onEvent(Event e){
                    doSomething(e);
                }
            });
        }
    }
    当ThisEscape发布EventListener时，它也无条件地发布了封装的ThisEscape实例，因为内引用类的实例包含了对封装实例隐含的引用。
    导致ThisEscape实例在构造时逸出。

  对象只有通过构造函数返回后，才处于可预言的、稳定的状态，所以从一个类构造函数内部发布的该类对象，只是一个未完成的构造对象，
  甚至即使是在构造函数的最后一行发布的引用也是如此。

  this引用在构造期间逸出的危害：
  1.一个导致this引用在构造期间逸出的常见错误，是在构造函数中启动一个线程，此时无论是显式的（将该线程参数传给构造函数）还是隐式地（Thread
  或者Runnable是所属对象的内部类），this引用几乎总是被新线程共享，于是新的线程能够在对象构造完成之前就能够看见它。

  在构造函数中创建线程并没有错误，但是最好不要立即启动它。而是发布一个start或者initialize方法来启动该线程，在构造方法执行结束之后，再调用该启动方法。

  2.在构造函数中调用一个可以覆盖的实例方法（不是private,不是final,不是static）也同样会导致this引用在构造期间逸出。

  如果想在构造函数中启动线程或者注册监听器类似的操作，可以使用一个私有的构造函数和一个公共的工厂方法，这样避免了不正确创建过程中的对象逸出。

    public class SafeListener {
    private final EventListener listener;

    private SafeListener(){
        listener = new EventListener(){
            public void onEvent(Event e){
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstance(EventSource source){
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
   }



3.3线程封闭
    线程封闭技术是实现线程安全的最简单的方式之一，当对象封闭在一个线程中时，此对象不会在多线程之间共享，自然是线程安全的。
    即使被封闭的对象本身并不是线程安全的，但是由于将该对象限制在一个线程内部，因此整个实现是线程安全的。

   线程池中的JDBC Connection对象是一种常见的使用线程限制的应用程序，在典型的服务器应用中，线程总是从池中获得一个Connection对象，
   并且用它处理一个单一的请求，最后将它归还。在Connection对象被归还之前，线程池不会再将该Connection分配给其他线程。

   1.Ad-hoc线程限制
   Ad-hoc线程限制是指维护线程限制性的任务全部落实在实现上（我理解的就是需要程序员手动实现将一个对象限制在一个线程内部）。
   因为没有可见性修饰符与本地变量等语言特性协助将对象限制在目标线程上，所以这种方式非常容易出错。

    线程限制的一种特例是将它用于volatile变量，只要你确保只是通过单一线程写入共享的volatile变量，那么在这些volatile变量
    上执行“读-改-写”操作就是安全的。在这种情况下，你就将修改操作限制在单一的线程中，从而阻止了竞争条件。并且，volatile的可见性
    保证其他线程能够看到变量的最新的值。

  2.栈限制
    在栈限制中，只能通过本地变量才可以触及对象。本地变量使对象更容易被限制在线程本地中，本地变量本身就被限制在执行线程中，他们存在于
    执行线程栈，其他线程无法访问这个栈。
    栈限制与ad-hoc线程限制相比，更容易维护，更加健壮。

  对于基本类型的本地变量，无需去尝试利用栈限制，因为无法获得基本类型的引用，所以语言语义确保了基本本地变量总是线程封闭的。
  可以看到在以下代码中，本地方法内部的引用animals, candidate，以及animals内部对象的引用都没有进行发布，返回值是基本数据类型
  因此不会存在对象的逸出问题。所有对象的引用都限制在本地方法栈内，只有通过方法内部的本地变量才可以触及对象，因此此方法内部的对象是
  线程安全的。

    public int loadTheArk(Collection<Animal> candidates){
        SortedSet<Animal> animals;
        int numPairs = 0;
        Animal candidate = null;

        animals = new TreeSet<Animal>(new SpeciesGenderComparator());
        animals.addAll(candidates);
        for(Animal a: animals){
            if(candidate == null || ！candidate.isPotentialMate(a)){
            candidate = a;
            }else{
                ark.load(new AnimalPair(candidate,a));
                ++numPairs;
                candidate = null;
            }
        }
        return numPairs;
    }


   3.ThreadLocal
   ThreadLocal提供了get与set访问器，为每个使用某个对象的线程维护一份单独的拷贝，所以get总是返回由当前线程通过set设置的最新值。
   关于ThreadLocal的原理去thread.md中查看。
   Spring中当JDBC使用ThreadLocal进行存储的情况，代码如下：

   private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        public Connection initialValue(){
            return DriverManager.getConnection(DB_URL);
        }
   };
   public static Connection getConnectino(){
        retuen connectionHolder.get();
   }
   其他应用：实现一个应用程序框架会广泛地使用ThreadLocal，在EJB调用期间，J2EE容器将一个事务上下文与一个可执行线程关联起来。
   它利用静态ThreadLocal持有事务上下文，当框架代码需要获知当前正在运行的是哪个事务时，它只需要从ThreadLocal中获得事务的上下文即可。
   这样降低了为每个方法传递上下文信息的需要，不过却增加了任何使用该机制的代码与框架之间的耦合。

3.4不可变性
    不可变对象永远是线程安全的。
    无论是Java语言规范还是Java存储模型都没有关于不可变性的正式定义，但是不可变性并不简单地等于将对象中的所有域都声明为final类型。
    所有域都是final类型的对象仍然可以是可变的，因为final域可以获得一个到可变对象的引用，即使该引用不变，但是该可变对象的状态是可变化的。
    "对象是不可变的"与"到对象的引用是不可变的"之间并不等同。

    只有满足以下状态，一个对象才是不可变的：
    1.它的状态不能在创建后再被修改；
    2，所有的域都是final类型，
    3.它被正确创建，创建期间没有发生this引用的逸出

   不可变对象不可以被更新吗？
    存储在不可变对象中的状态仍然可以通过替换一个带有新状态的不可变对象的实例得到更新。

  Final域
  final域使得确保初始化安全称为可能，初始化安全性让不可变性对象不需要同步就能自由地被访问和共享。？？
  原则：
  1.将所有的域声明为私有的，除非它们需要更高的可见性；
  2.将所有的域声明为final的，除非他们是可变的

  使用不可变对象保证线程安全性示例：

   @Immutable
   public class OneValueCache {

      private final BigInteger lastNumber;
      private final BigInteger[] lastFactors;


      public OneValueCache( BigInteger i, BigInteger[] factors){
          lastNumber = i;
          lastFactors = Arrays.copyOf(factors,factors.length);
      }

      public BigInteger[] getFactors(BigInteger i){
          if(lastNumber == null || !lastNumber.equals(i)){
              return null;
          }else{
              return Arrays.copyOf(lastFactors,lastFactors.length);
          }
      }
  }


   发布该对象:
   private volatile oneValueCache cache = new OneValueCache(null,null);

   当想要更新该对象的状态的时候，直接new OneValueCache(param1,param2),替代原来的不可变对象。
   当一个线程仅仅是持有该不可变对象时是不可以修改该对象的状态的，如果想要更新不可变对象的状态使用一个新的不可变对象
   替代原来的对象之后，因为变量是使用volatile修饰的，新的数据会立即对其他线程可见。


3.5安全发布
    如何安全地共享一个对象？简单地将对象的引用存储到公共域中，还不足以安全地发布它，如下面我经常使用的方式，就不是线程安全的。

    public Holder holder;

    public void initialize(){
        holder = new Holder(42);
    }

    由于没有限制holder的可见性，初始化线程在本线程内认为holder已经进行了初始化，但其他访问holder属性看到的有可能还未进行初始化。？？
    这种不正确的发布导致其他线程可以观察到“局部创建线程”。

    public class Holder {

        private int n;

        public Holder(int n){
            this.n = n;
        }

        public void assertSanity(){
            if(n !=n ){
                throw new AssertionError("This statement is false.");
            }
        }
    }
    其他访问线程看到的可能是一个没有被初始化完全的holder，n=0 因此调用assertSanity()时，会报错。


  不可变对象与初始化安全性
    Java存储模型为共享不可变对象提供了特殊的初始化安全性的保证，**即使发布对象引用时没有使用同步，不可变对象仍然可以被
    安全地访问。**不存在上述示例中变量n的初始化问题
    这个保证还会延伸到一个正确创建的对象中所有final类型域的值，没有额外的同步，final域也可以被安全的访问。
    然而，如果final域指向可变对象，那么访问这些对象的状态时仍然需要同步。


  安全发布的模式
      为了安全地发布对象，对象的引用以及对象的状态必须同时对其他线程可见，一个正确创建的对象可以通过下列条件安全的发布：
      1.通过静态初始化器初始化对象的引用；
        public static Holder holder = new Holder(42);
        静态初始化器由JVM在类的初始阶段执行，由于JVM内在的同步，该机制确保了以这种方式初始化的对象可以被安全的发布。
      2.将它的引用存储到volatile域或者AtomicReference;
      3.将它的引用存储到正确创建的对象的final域中；
      4.或者将它的引用存储到由锁正确保护的域中。

  线程安全容器的同步原理：
    将对象置入这些线程安全容器的操作，是将这个对象的引用存储到由锁正确保护的域中。
    如果线程A将对象X置入某个线程安全容器，随后线程B重新获得X，这时可以保证B所看到X的状态，正是A设置的，尽管程序并没有对X
    进行显式的同步。





