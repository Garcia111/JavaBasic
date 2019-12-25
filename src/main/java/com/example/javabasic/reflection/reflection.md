反射
  1>.Class对象引用
        类是程序的一部分，每个类都有一个Class对象。每当编写并且编译了一个新类，就会产生一个Class
        对象，为了生成这个类对象，运行这个程序的Java虚拟机将使用被称为"类加载器"的子系统。
   类加载器
        类加载器子系统实际上包含一条类加载器链，但是只有一个原生类加载器，原生类加载器加载的是可信类，包括
        Java API类，它们通常是从本地盘加载的。

        在类加载器链中通常不需要添加额外的类加载器，但是如果你有特殊需求，例如想要以某种特殊的方式加载类，
        那么你有一种方式可以挂接额外的类加载器。

   所有的类都是在对其第一次使用时， 动态加载的JVM中的，当程序创建第一个对类的【静态成员】的引用时，就会加载
   这个类。

      这个证明构造器也是类的静态方法，即使构造器方法并没有使用static关键字修饰，因此使用new操作符创建类的新对象
      也会被当做对类的静态成员的引用。

   类加载器首先检查这个类的Class对象是否已经加载，如果尚未加载，默认的类加载器就会根据类名查找.class文件，
   这个类的字节码在被加载时，它们会接受验证，以确保其没有被破坏，并且不包含不良Java代码。一旦某个类的Class对象
   被载入内存，它就会被用来创建这个类的所有对象。

   获取Class对象的引用的方法
    1.Class.forName();-----会在运行时受到检查
    2.如果已经获得了该类的对象，就可以调用该对象的getClass()方法来获取Class引用；----会在编译期受到检查
    3.类名.class----会在编译期受到检查，不会再编译期受到检查就不需要置于try语句块中；
    使用.class语法来获得对类的引用不会引发Class对象的自动初始化。使用Class.forName()立即就进行了初始化.

   为了使用类而做的准备工作实际上包含三个步骤：
   1.加载

        这是由类加载器执行的，该步骤将查找字节码（通常在classpath所指定的路径中查找），并从这些字节码中创建一个Class对象。

   2.链接

        在链接阶段将验证类中的字节码，为静态域分配存储空间，并且如果必须的话，将解析这个类创建的对其他类的所有引用。

   3.初始化

        如果该类具有超类，则对其初始化，执行静态初始化器和静态初始化块。
        note:初始化被延迟到了对【静态方法（包括构造器）】或者【非常数熟静态域】进行首次引用时才执行。

   Class中的重要方法：




  2>.类字面常量:类名.class
    这样获取Class引用的当时更加安全，因为它在编译时就会受到检查。
    对于基本数据类型的包装类，还有一个标准字段TYPE，TYPE字段是一个引用，指向对应的基本数据类型的Class对象。
    boolean.class == Boolean.TYPE
    char.class == Character.TYPE
    byte.class == Byte.TYPE
    short.class == Short.TYPE
    int.class == Integer.TYPE
    long.class == Long.TYPE
    float.class == Float.TYPE
    double.class == Double.TYPE
    void.class == Void.TYPE
    

  3>泛化的Class引用
    Class引用总是指向某个Class对象，它可以制造类的实例：
   1.普通的类引用可以被重新赋值为指向任何其他的Class独享

            Class intClass = int.class;
            intClass = double.class;

   2.泛型类引用只能赋值为指向其声明的类型

            Class<Integer> genericIntClass = int.class;
            genericIntClass = Integer.class;
            //编译无法通过 genericIntClass = Double.class;

            //下面无法编译通过，因为Integer Class对象不是Number Class对象的子类
            //Class<Number> genericNumberClass = int.class;

   3.使用通配符？,表示任何事物。

            Class<?> generalClass = int.class;
            generalClass = double.class;
            generalClass = Double.class;


   4.为了创建一个Class引用，它被限定为某种类型，或者该类型的任何子类型，
        将通配符与extends关键字相结合，创建一个范围：

            Class<? extends Number> bounded = int.class;
            bounded = double.class;
            bounded = Number.class;  //编译可以通过

    推荐使用泛型化的Class引用 Class<?>  class<? extends 父类>

   5.使用newInstance()为Class引用创建类对象的前提是，该Class对应的类需要有一个默认的无参构造器，否则将会产生一个异常；
   当使用Class泛型引用的newInstance()将返回该对象的确切对象，而不仅仅是基本的Object类型。

           Class<FancyToy> ftClass = FancyToy.class;
           FancyToy fancyToy = ftClass.newInstance();//生成的是确切的类型

           Class<? super FancyToy> up = ftClass.getSuperclass();

           //Class<Toy> up2 = ftClass.getSuperclass();---- /**编译无法通过*/

           Object obj = up.newInstance();//虽然是创建的Object类型，但是指向的是Toy对象的引用
           System.out.println(obj.getClass());

   6.新的类型转换方法

        1)将父类型的引用转换为子类型的引用的普通转换方法为：

            Building b = new House();
            House h = (House)b;

        2)新的类型转换方法为：

            Building b = new House();
            Class<House> houseType = House.class;
            House h= houseType.cast(b);

          新的转型语法对于无法使用普通转型的情况显得非常有用
    Initable.java
    仅仅使用.class语法来获得对类的引用不会引发初始化。
    但是，为了产生Class应用，Class.forName()立即就进行了初始化.
    如果一个static final值是“编译期常量”，就像Initable.staticFinal那样，那么这个值不需要对Initable类进行初始化就可以被读取
    如果仅仅是将一个域设置为static和final的，还不能确保不初始化类就可以被读取。
    例如，对Initable.staticFinal2d的访问将强制进行类的初始化，因为它不是一个编译期常量。

    如果一个static域不是final的，那么在对它访问时，总是要求在它被读取之前，要先进行链接（为这个域分配存储空间）和初始化（初始化该存储空间）


  动态的instanceof： isInstance

    Class对象 isInstance 类对象 = 类对象 instanceof Class对象
    都可以用来判断一个对象是否是某种类型
    instanceof和isInstance指的是：你是这个类吗，或者你是这个类的派生类吗？
    直接用==或者equal比较实际的Class对象：没有考虑继承，它或者是这个确切的类型，或者不是


  Class.isAssignableFrom

    AClass.isAssignedFrom(BClass):
    1.AClass是BClass的父类或者父接口-----返回true;
    2.AClass与BClass是同一个类或者同一个接口------返回true
    其他情况返回false；


  运行时的类信息：
    Class类与java.lang.reflect类库可以支持反射，该类库包含了Field Method以及Constructor类，这些类型的对象是由
    JVM在运行时创建的，用来表示未知类里面对应的成员。这样匿名对象的类信息就能在运行时被完全确定下来，而在编译时不需要知道任何事情。

  RTTI与反射之间真正区别只在于：
    1.RTTI：编译器在编译时打开和检查.class文件，也就是我们可以通过普通的方式调用对象的所有方法
    2.反射机制：.class文件在编译时是不可获取的，是在运行时打开和检查 .class文件