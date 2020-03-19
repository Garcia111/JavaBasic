谨慎修改SerialVersionUID的值: https://mp.weixin.qq.com/s/pgZF20ZTmZHVwo6vcPdfpg

序列化是一种对象持久化的手段，类通过实现java.io.Serializable 接口以启用其序列化功能。

阿里巴巴Java开发手册中规定：

  强制序列化类新增属性时，请不要修改SerialVersionUID字段，避免反序列失败；如果完全不兼容升级，避免反序列化
  混乱，那么请修改SerialVersionUID值。

   【java.io.Serializable接口是如何保证只有实现了此接口的方法才能进行序列化与反序列化的呢？】

   在进行序列化操作时，会判断要被序列化的类是否是Enum、Array、Serializable类型，如果都不是则直接抛出
   NotSerializableException。
   Java中还提供了Externalizable接口，也可以实现它来提供序列化能力。


  【Externalizable 接口】
  Externalizable继承自Serializable， 该接口中定义了两个抽象方法：withExternal()和readExternal()。
  当使用Externalizable接口来进行序列化与反序列的时候需要开发人员重写writeExternal()与readExternal()方法，
  否则所有变量的值都会变成默认值。


  【transient关键字】
  使用transient关键字控制变量的序列化，在变量声明前加上该关键字，可以阻止该变量被序列化到文件中，在被反序列化之后，
  transient关键字修饰的变量的值被设为初始值如int型的是0，对象型的是null。
  例如：一个用户有一些敏感信息如银行卡号 密码，为了安全起见，不希望在网络上操作中被传输，这些信息对应的变量就可以加上
  transient关键字。换句话说，这个字段的生命周期仅存在于调用者的内存中，而不会写到磁盘里面持久化。其他见transient.md


  【自定义序列化策略】
  在序列化过程中，如果被序列化的类中定义了writeObject和readObject方法，虚拟机会试图调用对象类里面的writeObject和
  readObject方法，进行用户自定义的序列化和反序列化。
  如果没有这样的方法，则默认调用是ObjectOutputStream 的 defaultWriteObject 方法以及 ObjectInputStream的
  defaultReadObject 方法。
  用户自定义的 writeObject 和 readObject 方法可以允许用户控制序列化的过程，比如可以在序列化的过程中动态改变序列化
  的数值。所以，对于一些特殊字段需要定义序列化的策略的时候，可以考虑使用transient修饰，并自己重写writeObject 和
  readObject 方法， 如 java.util.ArrayList 中就有这样的实现。


  【什么是serialVersionUID】
  序列化是将对象的状态信息转换为可存储或传输的形式的过程。
  我们都知道，Java对象是保存在JVM的堆内存中的，也就是是说，如果JVM堆不存在了，那么对象也就跟着消失了。
  序列化提供了一种方案，可以让你再即使JVM停机的情况下也能把对象保存下来的方案。就如我们平时使用的U盘一样，将Java对象序列化
  成可以存储或传输的形式（如二进制流），比如保存在文件中，这样当再次需要这个对象的时候，从文件中读取出二进制流，
  再从二进制流中反序列化出对象。

【serialVersionUID被修改了是怎样】
  虚拟机是否允许反序列化，不仅取决于类路径和功能代码是否一致，一个非常重要的一点是两个类的序列化ID 是否一致。
  在进行反序列化时，JVM会将传来的字节流中的serialVersionUID与本地相应实体类的serialVersionUID进行比较，
  如果相同就认为是一致的，可以进行反序列化，窦泽就会出啊先序列化版本不一致的异常，即java.io.InvalidCastException

  这也是《阿里巴巴Java开发手册》中规定，在兼容性升级中，在修改类的时候，不要修改SerialVersionUID的原因，
  除非是完全不兼容的两个版本，所以SerialVersionUID其实四验证版本一致性的。可以看到各个版本的JDK代码中
  向下兼容的类例如String类中的SerialVersionUID一直都是-6849794470754667710L。

  一旦类实现了Serializable，需要明确定义一个serialVersionUID,否则在修改类之后，系统会为该类生成一个新的
  serialVersionUID,在对该类进行反序列化的时候就会发生InvalidClassException



  



















