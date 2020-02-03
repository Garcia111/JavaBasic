Java并发编程实战第四章 ：组合对象

4.1设计线程安全的类
    设计线程安全类的过程应该包括下面三个基本要素：
    1.确定对象的状态由哪些变量构成；
    2.确定限制状态变量的不变约束；
    3.制定一个管理并发访问对象状态的策略；

   必须在单一的原子操作中获取或者更新相互关联的变量。


   4.1.2状态依赖操作：
   某些对象的方法也有基于状态的先验条件，例如：你无法从空队列中移除一个元素，在删除元素之前，队列必须处于非空状态。
   如果一个操作存在基于状态的先验条件，则该操作是状态依赖操作。

   使用Java内置的wait/notify机制，可以使某个操作等待先验条件成真。也可以使用现有类库来提供期望的状态依赖行为，
   比如阻塞队列(blocking queue)或者**信号量**(semaphore)，以及其他同步工具(Synchronizer)。

   4.1.3 状态所有权
     所有权与封装性总是在一起出现的，对象封装它拥有的状态，并且拥有它封装的状态。一旦一个对象将其拥有的状态的引用
     发布到一个可变对象上，它就不再拥有独占的控制权。
     类通常不会拥有由构造函数或者方法传递进来的对象，除非该方法是被明确设计用来转换传递对象的所有权的。


   **容器类通常表现出一种“所有权分离”的形式，这是指容器拥有容器框架的状态，而容器代码拥有存储在容器中的对象的状态。也就是容器并不
   持有其中存储的对象的状态，只是代为保管**。

   以servlet框架中的ServletContext为例，ServletContext为Servlet提供了类似于Map的对象容器服务，Servelet可以通过名称，
   使用setAttribute和getAttribute在ServletContext中注册于重获应用程序对象，由于Servlet容器实现的ServletContext
   对象一定会被多个线程访问，因此ServletContext必须是线程同步的，所以在调用setAttribute 和 getAttribute时，Servlet
   是不必同步的，但是使用存储在ServletContext中的对象时，可能必须要同步。

    synchronized(getServletContext()){
            getServletContext().setAttribute("bar", "16");
            out.print(getServletContext().getAttribute("bar"));
    }

  为什么？
    这些对象属于应用程序，ServletContext只是存储它们，并代替应用程序保管它们。正如所有的共享对象那样，它们必须安全地被共享，
    为了防止多线程并发访问同一对象所带来的干扰，这些对象应该是线程安全对象，高效不可变对象或者由锁明确保护的对象。


4.2 实例限制

   **将数据封装在对象内部，将对数据的访问限制咋对象的方法上，更易确保线程在访问时总能获得正确的锁。**
   实例限制是构建线程安全类的最简单的方法之一，它允许不同的锁保护不同的状态变量。

   线程安全类包装器的原理：
   类库中提供了包装器工厂方法Collections.synchronized及其同族的方法，使得非线程安全的类可以安全地用于多线程环境中。
   这些工厂方法利用Decorator模式，使用一个同步的包装器对象包装容器，包装器将相关接口的每个方法实现为同步方法，并将请求
   转发到下层的容器对象上，只要包装器对象占有着对下层容器唯一的可触及的引用，包装器对象就是线程安全的。

 4.2.1 Java监视器模式
   遵循Java监视器模式的对象封装了所有的可变状态，并由对象自己的内部锁保护。Java的内置锁也被称为 监视器锁 或 监视器。

    //私有锁对象
    public class PrivateLock {
       private final Object myLock = new Object();

       @GuardedBy("mylock")
       Widget  widget;

       void someMethod(){
           synchronized (myLock){
               //访问或修改widget的状态
           }
       }
    }

   私有锁对象 vs 对象的内部锁（其他可以公共访问的锁）
   私有的锁对象可以封装锁，客户代码无法获得该锁，
   使用可公共访问的锁允许客户代码涉足它的同步策略，可能会导致客户代码不正确的获得锁。而验证程序是否正确地使用一个可公共访问的
   锁，需要检查完整的程序，而不是一个单独的类。

4.3 委托线程安全
    可以将线程安全委托给一个单一的线程安全的状态变量；
    也可以将线程安全委托到多个隐含的状态变量上，只要这些变量是彼此独立的。例：NumberRange.java
        如果一个类由多个彼此独立的线程安全的状态变量组成，并且类的操作不包含任何无效状态转换时，可以将线程安全委托
        给这些状态变量。

    非常类似volatile变量的规则：当且仅当一个变量没有参与那些设计其他状态变量的不变约束时才适合声明为volatile类型

   4.3.4 发布底层的状态变量

        如果一个状态变量是线程安全的，没有任何不变约束限制它的值，并且没有任何状态转换限制它的操作，那么它可以被安全发布。

4.4 向已有的线程安全类添加功能
        大部分时候，一个类只支持我们需要的大部分操作，这时我们需要在不破坏其线程安全性的前提下，向它添加一个新的操作。

   1.添加一个新原子操作的最安全的方式是：修改原始的类，以支持期望的操作。这意味着所有实现同步策略的代码仍然在一个
            源代码文件中，便于理解和维护。
   2.另一种方法是扩展这个类，但是并非所有的类都给了子类足够多的状态，以支持这种方案。另外，扩展之后，同步策略的
        实现会被分布到多个独立维护的源代码文件中，所以扩展一个类比直接在类中加入代码更加脆弱。 如果低层的类选择了
        不同的锁保护它的状态变量，从而会改变它的同步策略，子类就在不知不觉中被破坏，因为它不能再用正确的锁控制对基
        类状态的并发访问,如下代码所示：


        public class BetterVector<E> extends Vector<E>{
            public synchronized boolean putIfAbsent(E x){
                boolean abset = !contains(x);
                if(absent){
                    add(x);
                }
                return absent;
            }
        }


  3.客户端加锁，第三个策略是扩展功能，而不是扩展类本身，并将扩展代码置入一个"助手"类。
        对于一个由Collections.synchronizedList封装的ArrayLit，两种方法，向原始类加入方法或者扩展类都不正确，
        因为客户代码甚至不知道同步封装工厂方法返回的List对象的类型。

        note:为了让这个方法正确工作，我们必须保证新添加方法所使用的锁，与List用于客户端加锁与外部加锁时所用的锁是同一个锁。

        @ThreadSafe
        public class ListHelper<E>{
            public List<E> list = Collections.synchronizedList(new ArrayList<E>());

            ...

            public boolean putIfAbsent(E x){
                synchronized(List){
                    boolean absent = !list.contains(x);
                    if(absent){
                        list.add(x);
                    }
                    return absent;
                }
            }
        }

  4.组合
   向已有的类中添加一个原子操作，还有一个更加健壮的选择：组合。
   如下代码中ImprovedList通过将操作委托给底层的List实例，实现了List的操作，同时还添加了一个原子的putIfAbsent方法，
   就像Collections.synchronizedList和其他容器封装器那样，ImprovedList假设一旦有一个list传给它的构造函数之后，
   客户将不再直接使用这个list,而是仅仅通过ImprovedList访问它。

   public class ImprovedList<T> implements List<T>{

        //private 限制了对list的访问
        private final List<T> list;

        public ImprovedList(List<T> list){
           this.list = list;
        }

        public synchronized boolean putIfAbsent(T x){
            boolean contains = list.contains(x);
            if(contains){
                list.add(x);
            }
            return !contains;
        }

        public synchronized void clear(){
            list.clear();
        }

        ...similarly delegate other List methods
    }

   ImprovedList使用Java监视器模式有效地封装了一个已有的List，不管List是否线程安全，ImprovedList都有自己兼容
   的锁可以提供线程安全性。


4.5同步策略的文档化
       每次使用synchronized、volatile或者任何线程安全的类，都表现了一种同步策略，这项策略是为了确保处于并发访问
   中的数据的完整性，这个策略是你程序设计的一个元素，因此应该将它文档化。设计的时候是编写设计决策文档的最佳时间，几周
   或者几个月之后，一些设计细节会变得模糊----所以在你忘记之前将它写下来。

    1.哪些变量声明为volatile类型；
    2.哪些变量被锁保护；
    3.哪些锁保护哪些变量；
    4.哪些变量是不可变的或者被限制在线程中的；
    5.哪些操作必须是原子的
    .....等等


  4.5.1 含糊不清的文档
        从规范实现者的角度去解读规范，而不是从规范使用者的角度去考虑。
















