
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

6. **Spring循环依赖**
    单例模式的bean---基于Spring的缓存
    原型的bean------在使用到的时候才会去new 会发生循环依赖
    