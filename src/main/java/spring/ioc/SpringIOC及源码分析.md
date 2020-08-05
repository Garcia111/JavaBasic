
1.单纯的基于注解需要在xml中开启，基于JavaConfig完全不需要xml??
 ClassPathXmlApplicationContext不具备解析注解的功能，AnnotationConfigApplicationContext具备解析注解的功能；

IOC：
    也称为控制反转，是一种设计思想。在传统的JAVA SE程序中，我们直接在对象内部通过new的方式来创建对象，
    是程序主动创建依赖对象；而在Spring程序设计中，IOC是由专门的容器去控制对象，可以用来减低计算机代码之间的耦合度。
    
    所谓控制就是对象的创建,初始化和销毁。
        创建对象：原来是new一个，现在是由Spring容器创建；
        初始化对象：原来是对象自己通过构造器或者setter方法给依赖的对象赋值，现在由Spring容器自动注入；
        销毁对象：原来是直接给对象赋值null或者做一些销毁操作，现在是Spring容器管理生命周期负责销毁对象。
        
依赖注入DI：Dependency Injection
    依赖注入是一种实现，而IOC是一种设计思想。从IOC到DI就是从理论到时间内。容器把依赖交给容器，容器帮你管理依赖，这就是依赖注入的核心。
    
    
Spring实现IOC的思路与方法：

    Spring实现IOC的思路是提供一些配置信息来描述类之间的依赖关系，然后由容器去解析这些配置信息，
    继而维护好对象之间的依赖关系，前提是对象之间的依赖关系必须在类中定义好，比如A.class中有一个B.class
    的属性，那么我们可以理解为A依赖了B。
    
   Spring实现IOC的思路大致可以分为3点：
   
    1.提供Java类，类中定义了各个实体之间的依赖关系
    2.将需要交给容器管理的对象通过配置信息告诉容器（方式可以有xml annotation javaconfig）
    3.把各个类之间的依赖关系通过配置信息告诉容器
   
**自动装配**

    【既然在类中已经定义了他们之间的依赖关系，为什么还需要在配置文件中描述和定义呢？】
    上面说过，IOC的注入由两个地方需要提供依赖关系，一个是在类中，二是在Spring的配置中需要去描述。自动装配则把第二个取消了，
    即我们仅仅需要在类中提供一类，继而把对象交给容器管理既可以完成注入。    
    在实际开发中，描述类之间的依赖关系通常是大篇幅的，如果使用自动装配则省去了很多配置，并且如果对象的依赖发生更新，我们可以
    不去更新配置。
   
  自动装配的设置：
  
    1.no----不使用自动装配；
    2.byName---通过beanName进行装配，这里需要注意的是，如果A类中依赖的B类的对象为b，这里的byName指的并不是b，二是指A类中
                对于B类对象的set方法
                
      如：
      class A{
        private B b;
        
        public void setB(B b){
         this.b = b;
        }
      }
      如果你由两个B类的对象b1 b2，需要在A类中指明需要注入b2，这时如果自动装配的方式为byType，就会启动报错，因为Spring不知道该
      注入b1 还是b2，如果自动装配方式设置为ByName，则需要修改A中B类对象的set方法名称，而不是对象的名称：
      class A{
         private B b;
              
         public void set2(B b){
            this.b = b;
         }
      }   
    
    3.byType-----通过类的类型进行注入，此时注意需要使用的类型只能有一个对象
    4.constructor----类似于byType，但是适用于构造函数参数，如果容器中没有构造函数参数类型的确切bean,就会报错。
    
@Autowired与 @Resource的区别：    

    @Autowired---使用@Autowired默认使用的自动装配方式是byType，如果使用byType的方式没有找到，则会继续使用
                  byName的方式进行查找，如果还找不到则会报错
    @Resource----使用@Resource则默认使用的byName的自动装配方式，
                 与之前在xml文件中指明自动装配方式不同的是，这里的byName是指变量的名字，而不是set方法的名字；
                 @Resource除了可以使用默认的byName装配方式，也可以使用属性指明要注入的bean的类型，也相当于
                 可以使用byType进行注入。
                 
 使用BeanNameGenerator实现bean的自定义命名,这与@Service("自定义名称")相比有什么优势吗？
 
 Spring的懒加载
 
 Spring的作用域
    Singleton Beans with Prototype-bean Dependencies
    在一个singleton的bean实例A中引用了一个Prototype的bean B，则B对象的prototype失去了原有的意义
    但是确实有情况需要在每次初始化单例的bean A的时候每次都生成一个原型的B,这时要怎么实现呢？
    1.实现ApplicationContextAware接口
    2.使用@lookup注解
    
    
    
    
    
    
    
    
    
    
    
    
    
                 