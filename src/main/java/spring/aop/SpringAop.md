鲁班学院

    1.Spring IOC 与 DI有什么区别？
        DI是实现IOC的一种技术手段


Spring的生命周期和回调

    Spring在容器初始化bean之后和销毁之前都提供了回调的方法，我们称之为生命周期的回调
    方法，Spring中提供了三种方式来完成生命周期的回调。

  1、接口方式

     实现Spring中的InitializingBean 和DisposableBean 接口，实现其对应的
     afterPropertiesSet()以及destroy()方法。
        afterPropertiesSet():会在Spring容器完成属性注入之后进行调用；
        destroy()：将在Spring销毁bean之前进行调用

     缺点：让你的代码和Spring耦合

  2、注解方式

     通过使用@PostConstruct 和 @PreDestroy注解也可以完成相同的功能，这两个注解是由JDK提供的
     Spring通过*CommonAnnotationBeanPostProcessor* 这个后置处理器进行解析和处理。

  3、init-method 和 destroy-method方式

    在使用xml来进行bean元数据的配置的时候，我们可以使用init-method或者destroy-method属性来
    指定具体的方法作为初始化回调方法。
    <bean id = "testBean" class="examples.testBean" destroy-method="destroy"/>


三种方案的执行顺序
    在一个bean中可以同时按照以上三种方式来指定三个生命周期回调方法，它们的执行顺序是
    1.注解方式
    2.接口方式
    3.通过xml指定的形式









