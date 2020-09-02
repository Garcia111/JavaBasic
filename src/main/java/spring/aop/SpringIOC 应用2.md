
1. @ComponentScan:可以通过 basePackages  includeFilter和 excludeFilter指明基础扫描包下的哪些类在扫描范围内，哪些被排除
2. 生成候选组件的索引

   尽管类路径扫描非常快，但是可以通过在编译时创建静态候选列表来提高应用程序的启动性能。
   要生成索引，需要添加如下依赖关系：

        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context-indexer</artifactId>
                <version>5.2.7.RELEASE</version>
                <optional>true</optional>
            </dependency>
        </dependencies>

   对于Gradle 4.5和更早版本，应在compileOnly 配置中声明依赖项，如以下示例所示：

    dependencies{
        compileOnly "org.springframework:spring-context-indexer:5.2.7.RELEASE"
    }

   对于Gradle 4.6及更高版本，应在annotationProcessor 配置中声明依赖项，如以下示例所示：

    dependencies {
        annotationProcessor "org.springframework:spring-context-indexer:{spring-version}"
    }

3. Spring的一些注解可以使用javax的一些注解来替代，但是这些javax的注解有一定的局限性

4. @Profile的使用-----作业

5. 我们的服务改造了之后，dataSource这些配置去哪里了，直接通过配置中心的配置文件进行设置了吗

6. **Spring循环依赖**？？
    单例模式的bean---基于Spring的缓存
    原型的bean------在使用到的时候才会去new 会发生循环依赖

7. Introductions 引入

    @DeclareParents(value = "spring.aop.dao.*+",defaultImpl = IndexDao.class)
    public static Dao dao;

    使用说明：spring.aop.dao包下的所有类，不管其是否实现了Dao接口，通过切面使其实现Dao接口，并为其提供默认的实现类IndexDao

8. AspectJ的实例化模型
    默认情况下，每个切面都是单例的，称为ApectJ实例单例化模型。Spring支持将切面定义为原型模式的，
    就是每次调用切面方法都会产生一个新的切面。Spring支持perthis和pertarget实例化模型。
    如何实现切面实例为原型prototype模式?

     @Aspect("perthis(this(spring.aop.dao.IndexDao))")
     可以使用此方式来指明具体哪个切面使用的是prototype模式，其他切面则仍然是singleton模式的。
     【注意】：target对象也必须是prototype的时才会生效，因为只有目标对象是原型模式时，每次getBean得到的对象才是
     不一样的，因此针对每个对象就会产生新的切面对象，才能产生不同的切面实例的效果。


9.声明式事务与AOP的关系
    声明式事务只是借助了AOP技术。。。
    xml支持的Advisor声明式事务管理，AspectJ并不支持，Spring自己开发了一种技术 @Transaction

















