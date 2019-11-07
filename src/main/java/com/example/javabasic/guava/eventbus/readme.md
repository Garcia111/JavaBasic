1.什么是EventBus
    总线Bus 一般指计算机各种功能部件之间传递信息的公共通信干线，而EventBus则是事件源（publisher）向订阅方（Subscriber）发送订阅时间的总线。
    它解耦了观察者模式中订阅方和事件源之间的强依赖关系。

2.EventBus有三个操作：注册:Listener--register
                  注销:Listener--unregister
                  发布: Event--post
在19版本之前，Listener的注册和注销，事件的发布，由EventBus完成；
在19版本之后，EventBus把Listener的注册和注销工作委托给 SubscribeRegistry,将时间发布的工作委托给Dispatcher完成。
这样修改职责分明，在并发方面也有了很大的改变，以前，对维护某类事件和关于此类时间感兴趣的Subscribe的操作用了读写锁来控制对容器的操作；
19版本之后，使用了并发容器ConcurrentMap<Class<?>,CopyOnWriteArraySet<Subscriber>>避免了对锁的使用

ConcurrentMap<Class<?>,CopyOnWriteArraySet<Subscriber>> ---------？？？？？

    1》SubscribeRegistry
        在观察者模式中，事件源中会维护一个Listener的列表，而且向这个事件源注册的Listener一般只会收到一类事件的通知。如果Listener
        对不同类的时间感兴趣，则需要向多个事件源注册。EventBus是怎样实现Listener一次注册，能够知道Listener对哪些事件感兴趣的，
        进而 在某类事件发生时通知到Listener的呢？

        答案在SubscribeRegistry这个类中，在SubscribeRegister有一个实例属性
        ConcurrentMap<Class<?>,CopyOnWriteArraySet<Subscriber>> subscribers，它维护了某个事件类型和对其感兴趣的
        Subscribe的列表。

        register(Object listener)的工作就是找出这个Listener对哪些事件感兴趣，然后把这种事件类型和该Listener构建成的Subscriber加到subscribers中。
        unregister的过程和register类似，只从subscribers删掉Listener感兴趣的事件。

        SubscribeRegister通过reflection找出该Listener对象，哪些Method用了@Subscriber注解了，用@Subscriber注解的方法表示当
        某事件发生时，希望收到事件通知。在@Subscriber注解的方法中只能包含一个参数，那就是Event，否则会出错。
        在reflection的时候用LoadingCache<Class<?>,ImmutableList<Method>> subscriberMethodCache 缓存了该Listener Class
        和对应的Method。

        在构建Subscriber的时候根据方法是否有@AllowConcurrentEvents 分为同步和并发执行method。


3.MultiListener的使用：只需要在要订阅消息的方法上加上@Subscribe注解即可实现对多个消息的订阅;
4.Event的继承(example3包)：如果Listener A监听Event A, 而Event A有一个子类Event B, 此时Listener A将同时接收Event A和B消息