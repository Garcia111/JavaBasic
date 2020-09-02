
一、Maven的仓库管理、依赖管理、继承和聚合等特性为项目的构建提供了一套完善的解决方案。
如果搞不懂Maven，那么一个多模块的项目足以让你头疼，依赖冲突就会让你不知所措。

什么是本地仓库？Maven到底有哪些仓库？它们是什么关系？

    你要jar包，你不可能每次都去互联网下载，所以本地仓库就相当于加了一层jar包缓存，先到这里来查
    如果查不到，就到私服上去找。如果私服上也找不到，那么就去中央仓库找，找到jar后，会把jar的信息
    同步到私服和本地仓库中。

私服
    
    私服就是公司内部局域网内的一台服务器而已。当你的Project-A依赖别人的Project-B的接口，
    怎么做呢？
    
    没有Maven的时候，当然是copy Project-B jar到你本地的lib中引入。Maven的方式，很显然需要
    其他人把Project-B deploy到私服仓库供你使用。因此，私服中存储了本公司的内部专用jar。不仅如此，私服
    还充当了中央仓库的镜像，说白了就是一个代理。
    
中央仓库
    
    该仓库存储了互联网上的jar，由Maven团队来维护。地址是http://repo1.maven.org/maven2/
    
     <dependency>
                <groupId>com.google.guava</groupId>
                <artifactId>guava</artifactId>
                <version>25.1-jre</version>
     </dependency>
     
     其实这个标签揭示了jar的查找坐标：groupId artifactId  version
     一般而言，我们可以到私服上输入artifactId进行搜索，或者到http://search.maven.org/、
     http://mvnrepository.com/上进行查找确定坐标，version分为开发版本（Snapshot）
     和发布版本（Release），那么为什么要分呢？
     
     在实际开发中，我们经常遇到这样的场景，比如A服务依赖于B服务，A和B同时开发，B在开发中
     发现了Bug，修改之后将版本由1.0升级为2.0,那么A必须也跟着在POM.XML中进行版本升级。
     过几天之后，B又发现了问题，进行修改之后，升级版本发布，然后通知A进行升级...可以说这是开发
     过程中的版本不稳定导致了这样的问题。
     
     
     Maven已经替我们想好了解决方案，就是使用Snapshot版本，在开发过程中B发布的版本标志为
     Snapshot版本，A进行依赖的时候选择Snapshot版本，那么每次B发布的话，会在私服仓库中形
     成带有时间戳的Snapshot版本，而A构建的时候会自动下载B最新时间戳的Snapshot版本。
     

既然Maven进行了依赖管理，为什么还会出现依赖冲突？处理依赖冲突的手段是？

    首先对于Maven而言，同一个groupId同一个artifacetId下，只能使用一个version
    
    现在，我们可以思考下，比如工程需要引入A、B ，而A依赖1.0版本的C，B依赖2.0版本的C，那么问题
    来了，C的使用的版本将由A、B的顺序而定？
    这显然不靠谱，如果A的依赖写在B的依赖后面，将意味着最后引入的是1.0版本的C，
    这就很可能在运行阶段出现类（ClassNotFoundException）方法（NoSuchMethodError）找不到的
    错误，因为B使用的是最高版本的C。
    
这里其实涉及到了两个概念：依赖传递、Maven的最近依赖策略

依赖传递：
    如果A依赖B，B依赖C，那么引入A，意味着B和C将会都被引入。

Maven的最近依赖策略：
    如果一个项目依赖相同的groupId  artifact的多个版本，那么在依赖树（mvn dependency:tree）中
    离项目最近的那个版本将会被使用？？
    Gradle使用version+策略
    
如何处理依赖冲突呢？
1.要是想要使用哪个版本，我们是清楚的，那么能不能不管如何依赖传递，都可以进行版本锁定呢？
    使用<dependencyManagement>----主要用于子模块的版本一致性中
2.在依赖传递中，能不能去掉我们不想依赖的？
    使用<exclusions>----在实际中可以在IDEA中直接利用插件帮助我们生成。？？？什么插件

引入依赖时如何提前发现问题？？
    
    如果我们新加入一个依赖的话，那么先通过mvn dependency:tree命令形成依赖树 ？？如何使用呢？
    看看我们新加入的依赖，是否存在传递依赖，传递依赖中是否和依赖树中的版本存在冲入，
    如果存在多个版本冲突，利用上下文的方式进行解决！？？
    
Maven规范化目录结构
    /pom.xml
    
    /src/main/java
    /src/main/resources
    
    /src/test/java
    /src/test/resources
    
 第一：src/main下的内容最终会打包到Jar/War中，而src/test下是测试内容，并不会打包进去
 第二：src/main/resources中的资源文件会COPY至目标目录，这是Maven的默认生命周期中的一个规定动作。
 
 
 Maven的生命周期：
        clean
        validate
        compile
        test
        package
        verify
        install
        site
        deploy
        
        执行后面的命令时，前面的命令会自动得到执行。常用的命令如下：
           1.clean:清理；
           2.package:打包成jar 或者War包，会自动进行clean+compile
           3.install:将本地工程Jar上传到本地仓库
           4.deploy:上传到私服
           
scope的依赖范围：
    Maven的生命周期存在编译、测试、运行这些过程，那么显然有些依赖只用于测试，比如junit;
    有些依赖编译用不到，只有在运行时才能用到，比如mysql的驱动包在编译期就运行不到，编译时用的是JDBC接口，
    而是在运行时用到的；
    还有一些依赖，编译期要用到，而在运行期不需要提供，因为有些容器已经提供了，比如servlet-api在tomcat
    中已经提供了，我们只是需要在编译期提供。
    
  1.compile:默认的scope，运行期有效，需要打入包中；
  2.provided:编译期有效，运行期不需要，不会打入包中；
  3.runtime:编译期不需要，运行时需要，需要打入包中；
  4.test:测试需要，不会打入包中
  5.system:非本地仓库引入，存在系统的某个路径下的jar包--一般不使用。           
           
    
    
  
             
     
     
     
     
     
     