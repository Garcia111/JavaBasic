1.切点是连接点的集合
    连接点是一个个可以被增强的方法，切点则是所有这些可以被增强的方法的集合；
    com.javabasic.service.*Service-----所有的可以被增强的方法

2.通知就是代理逻辑加到连接点的位置，
    所以它其实是包含了两个部分的：1.要加入的代理逻辑；2.代理逻辑加入到连接点的位置

3.织入：织入就是把代理逻辑加入到目标对象上的过程

4.切面就是连接点  切点 通知这些内容的载体，就像一个类中有属性 有方法一样；


5.AspectJ-----一种AOP技术

6.AOP 与SpringAOP 的关系--新知识
  1）SpringAOP AspectJ 都是AOP的一种实现,
        早期SpringAOP 语法比较复杂，难用因此不为人们接受，后来SpringAOP 借助了 AspectJ的语法，
        但是底层技术还是用的Spring自己的AOP技术。
  2）在配置文件中添加 @EnableAspectJAutoProxy----代表程序支持了AspectJ的语法

  如果你需要对Spring管理的bean进行AOP增强，使用SpringAOP即可，但是如果需要通知增强对的对象不被Spring管理，则
  需要使用AspectJ。

  AspectJ是静态织入----生成的字节码文件已经具有通知的代码
  SpringAOP是动态织入

7.切点表达式---比较全面的了解
   https://shimo.im/docs/Nj0bcFUy3SYyYnbI/read


8.实现动态代理有JDK动态代理和CGLib动态代理，
    JDK动态代理使用实现与目标类相同的接口的方式
    CGLib动态代理使用的是继承目标类的方式
   问题：为什么JDK动态代理只能使用实现接口的方式呢？

    通过查看JDK动态代理生成的代理类可以看出，代理类继承了Proxy类，然后实现了与目标类相同的接口，因为Java不支持多继承，所以选择实现接口的方式

   问题2：实现Proxy类的作用？








