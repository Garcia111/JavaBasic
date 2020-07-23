Shiro

    常用的Java Web访问控制实施流程
        1.用户 向 [权限管理模块] 申请用户 申请权限
        2，用户使用用户名密码 登录， 使用 [安全管控模块] 做登录认证
        3.安全管控模块 向权限管理模块请求获取用户权限数据和用户数据

   **安全管控模块**常使用下列安全框架：

   Java Web 安全框架选型

    1.JAAS
      Java验证和授权API，jdk提供的一种标准化的方法，对于有**异构分布式需求**的大型企业推荐用这种标准化
      的方法，目前大部分的java web工程没有用此安全框架，时长占有率不高，很多开源项目用的安全框架就是jaas，
      比如 CAS tomcat activeMQ等等。

    2 Spring Security
        市场占有率最高的一个安全框架，支持范围广，功能更强大，但是比shiro复杂，学习成本高，同时它与spring的
        结合比较紧密，如果没有使用spring就不能使用spring security框架，对于一些安全需求复杂、支持范围较广
        的情况下，请使用spring security。

    3. shiro
        Apache项目，旨在简化认证和授权，简单，易学，高效，已经被广泛的采用。对于一般的java web项目，使用shiro
        作为安全控制已经足够。相对于spring security，shiro的优势体现在其独立性，就算工程中没有使用spring，
        也是可以使用shiro的，spring官网都使用shiro作为其安全控制的工具。

   Shiro架构
   1.Subject

         任何可以与应用交互的"用户"，这个用户不一定是一个具体的人，与当前应用交互的任何东西都是Subject,即与当前应用交互
         的任何东西都是Subject，如网络爬虫，机器人等，即一个抽象概念，所有Subject都绑定到SecurityManager,与Subject的
         所有交互都会委托给SecurityManager。
   2.SecurityManager:

        相当于Spring MVC的DispatcherServlet， 是Shiro的心脏，所有具体的交互都通过SecurityManager进行控制，
        它管理者所有的Subject，且负责进行认证、授权。会话及缓存的管理。
   3. Authenticator

         认证，是一个扩展点，可以自定义实现，可以使用认证策略，即什么情况下算用户认证通过了
   4.Authorizer

         鉴权：访问控制器，用来决定主体是否有权限进行相应的操作，即控制着用户能够访问应用中的哪些功能；
   5.Realm

         Shiro从Realm获取安全数据（如用户、角色、权限），SecurityManager要验证用户身份，那么它需要从Realm
         获取相应的用户进行比较以确定用户身份是否合法，也需要从Realm得到用户相应的角色/权限进行验证用户是否能够进行
         操作。
         可以有一个或者多个Realm，可以认为是安全实体数据源，即用于获取安全实体的，可以是JDBC实现，也可以是内存实现
         由用户提供，一般在应用中都需要实现自己的Realm_
   6.SessionManager

         会话管理，用户登录之后就是一次会话，在没有退出之前，它的所有信息都在会话中，会话可以是普通JavaSE环境的，
         也可以是Web环境的。
   7.SessionDao

        如果我们想将Session保存到数据库，可以实现自己的SessionDAO,通过JDBC写到数据库，比如想要将Session放到Memcached中
        可以实现自己的Memcached SessionDAO,另外SessionDAO中可以使用Cache进行缓存，以提高性能。
   8.CacheManager

        缓存控制器，来管理如用户 角色权限等的缓存，因为这些数据是很少改变，放在缓存中可以提高访问的性能
   9.Cryptography

         密码模块，Shiro提供了一些常见的加密组件，用于密码加密 解密，将密码加密存储到数据库，而不是明文存储
   10.Web Support

          Web支持，可以非常容易的集成到Web环境。
   11. Concurrency

          【Shiro支持多线程应用的并发验证，即如果在一个线程中开启另一个线程，能将权限自动传播过去；】
   12. Testing

          提供测试支持
   13. Run As

          允许一个用户假装为另一个用户（如果他们允许）的身份进行访问
   14. Remember Me

          一次登录之后，下次再来的时候不用再登录了。

   【Shiro不会去维护用户、维护权限，这些需要我们自己去设计/提供，然后通过相应的接口注入给shiro即可。】


   网上常见的shiro实例都是使用ini文件进行集成，shiro.ini文件的优缺点：
    1.INI文件优势： 简单易懂，集成方便；
    2.INI文件缺点： 只是适用于较少的用户系统，并且账号密码是固定的，采用硬编码方式将认证授权信息写在INI文件中，
                   可维护性差

  1)通过SecurityUtils得到Subject，其会自动绑定到当前线程，如果在Web环境在请求结束时需要解除绑定
  2)调用subject.login 方法进行登录，其会自动委托给SecurityManager.login方法进行登录；
  3)如果身份验证失败，请捕获AuthenticationException 或其子类，常见的有
        DisabledAccountException(禁用的账号)
        LockedAccountException(锁定的账号)
        UnknownAccountException(错误的账号)
        ExcessiveAttemptsException(登录失败次数过多)
        IncorrectCredentialsException(错误的凭证)
        ExpiredCredentialsException（过期的凭证）等
     对于页面的错误消息展示，最好使用如"用户名密码错误"而不是 "用户名错误"和"密码错误"，防止一些恶意用户非法扫描账号库


  1.提供Realm，需要继承AuthorizationRealm, 并覆盖其中的认证doGetAuthenticationInfo和授权doGetAuthorizationInfo两个方法
  2.用户名密码相关token需要继承UsernamePasswordToken;
  3.当访问到页面的时候，链接配置了相应的权限或者shiro标签才会执行doGetAuthorizationInfo方法否则不会执行
  4. doAuthorization方法对用户角色进行权限查找

ShiroFilter的工作原理
  1.ShiroFilter提供了与Web继承，其通过ShiroFilter入口来拦截需要安全控制的url，然后进行相应的权限控制。
  2.ShiroFilter 类似于Struts2/SpringMVC这种Web前端框架的前端控制器，是安全控制的入口点。

  使用filterChainDefinitions 详解：配置哪些页面需要受保护，以及访问这些页面需要的权限：
  1） .anon 可以被匿名访问
  2） .authc 必须认证（即登录之后）才可以访问的页面
  3） .logout 登出
  4） .roles 角色过滤器

  前端发起的所有请求都会被ShiroFilter拦截到，访问时，如果有权限或者允许匿名访问，则可以直接访问，否则将会
  被重定向到loginUrl。

ShiroFilterFactoryBean中FilterChainDefinitionMap属性设置：
   1 : [urls]部分的配置，其格式是："url=拦截器[参数],拦截器[参数]"
   2 : 如果当前请求的url匹配[urls] 部分的某个url模式，将会执行其匹配的拦截器
   3 : anon (anonymous) 拦截器表示可以匿名访问（即不需要登录就可以访问）
   4 : authc (authentication) 拦截器表示表示身份需要认证通过后才能访问
   5 : url匹配模式（url模式使用Ant风格），Ant路劲通配符支持?,*,** ，注意通配符匹配不包括目录分割符"/"
        1).-?:匹配一个字符，如/admin? 将匹配admin1 ，但不匹配 /admin 或 /admin/
        2).-*:匹配零个或多个字符串，如/admin* 将匹配 /admin123 , /admin ，但不匹配/admin/1
        3).-**:匹配路劲中有零个或多个路劲，如/admin/** 将匹配 /admin/123 , /admin/123/1
   6 : url匹配顺序（url权限采取第一次匹配优先的方式，即从头开始使用第一个匹配的url模式对应的拦截器链）
        -/bb/** = filter1
        -/bb/aa = filter2
        -/** = filter3
        如果请求的url是"/bb/aa",那么将使用filter1进行拦截

【认证】
Shiro的认证过程分析：
    1.调用SecurityUtils.getSubject 获取当前subject
    2.调用subject的isAuthenticated()测试当前的用户是否已经被认证，是否已经登录
    3.如果未认证，则将请求中获得的用户名密码封装为UserNamePasswordToken值
    4.执行登录：调用subject的login（AuthenticationToken）方法
    5.自定义Realm方法，从数据库中获取对应的记录，返回给Shiro
    6.由shiro完成密码比对
    7.自定义Realm中用户的认证过程
        1）把AuthenticationToken转换为UsernamePasswordToken
        2)从UsernamePasswordToken中取得Username，
        3）根据Username获取对应的用户记录
        4）若用户不存在可以直接抛出UnknownAccountException
        5)根据用户信息的情况，决定是否需要抛出其他AuthenticationException异常
        6)根据用户情况，构建AuthenticationException异常，通常实现类是SimpleAuthenticationInfo

密码的比对
    【密码的比对是使用Realm中的CredentialsMatcher属性来进行比对的。】
    1.UsernamePasswordToken中保存了前台页面传的用户名密码，SimpleAuthenticationInfo保存了从数据库中查询出来的用户名密码
    【密码的MD5加密】
    2.密码一般使用密文保存，可以使用CredentialsMatcher的子类HashedCredentialsMatcher来设置使用何种加密算法,加密的次数，是否使用盐等等
    3.使用HashCredentialsMatcher时，在比对UsernamePasswordToken和SimpleAuthenticationInfo中的密码时，会自动对UsernamePasswordToken
      中的密码进行加密；-----还有个问题，这种情况下难道前端在传给后端的过程中密码是明文传输的吗？
    4.如何做到即使两个人的密码一样，但是加密之后的密码是不一样的？
      使用盐进行加密，此时doGetAuthenticationInfo方法中返回的SimpleAuthenticationInfo 也应该是带有盐的，
      使用 SimpleAuthenticationInfo(Object principal, Object hashedCredentials, ByteSource credentialsSalt, String realmName)构造器
      通过不同的用户使用不同的盐进行加密，实现了每个人的加密后的密码也不同，即使原始密码相同。
      使用ByteSource.Util.bytes(Object source);方法来生成每个客户的唯一盐值。
    【多Realm的认证策略】
      1>多Realm的应用场景：
        1.不同的用户类型使用不同的Realm进行验证；
        2.可能验证用的用户名密码存在于不同的数据库中，使用不同的加密方法进行加密
      2>实现方法：
        配置SecurityManager中的Realms List
      3>如果有多个Realm的话，怎么才能算验证成功呢？
        配置ModularRealmAuthenticator中的AuthenticationStrategy属性
         。FirstSuccssfulStrategy: 只要有一个Realm验证陈宫即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略。
         。AtLeastOneSuccessfulStrategy（默认）:只要有一个Realm验证成功即可，和FirstSuccssfulStrategy不同，将返回所有
                                        Realm身份验证成功的认证信息；
         。AllSuccessfulStrategy:  所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了


【授权】
    授权，也叫做访问控制，即在应用中控制谁访问哪些资源（如访问页面/编辑数据/页面操作等）。在授权中需要了解的几个关键对象：主体（Subject）
    资源（Resource）权限（Permission）角色（Role）
    。主体-Subject：访问应用的用户，在Shiro中使用Subject代表该用户，用户只有授权后才允许访问相应的资源。
    。资源-Resource: 在应用中用户可以访问的URL，比如访问JSP页面、查看/编辑某些数据、访问某个业务方法、发音文本等等都是资源。
    。权限-Permission: 安全策略中原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权利。
      Shiro支持粗粒度权限（如用户模块的所有权限）和细粒度权限（操作某个用户的权限）
    。角色-Role: 权限的集合，一般情况下会赋予用户角色而不是权限，这样用户可以拥有一组权限，赋予权限时比较方便。


Shiro权限注解
    1. @RequiresAuthentication:表示当前Subject已经通过login进行了身份验证，即Subject.isAuthenticated()返回true
    2. @RequiredUser:表示当前Subject已经身份验证或者通过记住我扥估了的。
    3. @RequiredGuest:表示房钱Subject没有身份验证或通过记住我登陆过，即是游客身份
    4. @RequiredRoles(value = {"admin","user"},logical = Logical.AND):表示当前Subject需要角色admin和user
    5. @RequiredPermissions(value = {"user:a","user:b"},logical = Logical.OR):表示当前Subject需要权限user.:或者user:b


从数据表中初始化资源和权限
    使用数据表中的资源构建成一个LinkedHashMap  FilterChainDeginitionMap作为
    FilterChainDefinitions的属性


会话管理
    Shiro提供了完整的企业级会话管理功能，不依赖于底层容器（如web容器tomcat）,不管JAVA SE还是Java EE
    都可以使用，提供了会话管理、会话事件监听、会话存储/持久化、容器无关的集群、失效/过期支持、对WEB的透明支持
    SSO单点登录的支持性等特性。

    会话相关的API
    1.Subject.getSession()即可获取会话：
           Subject.getSession(true):如果当前没有创建Session对象会创建一个
           Subject.getSession(false):如果当前没有创建Session则返回null

    2.Session.getId():获取当前会话的唯一标识

    3.session.getHost():获取当前Subject的主机地址

    4.session.getTimeOut() & session.setTimeout(毫秒)：获取/设置当前Session的过期时间

    5.session.getStartTimestamp() & session.getLastAccessTime():获取会话的启动时间以及最后
    访问时间，如果是Java SE 应用需要自己定期调用session.touch()去更新最后访问时间；如果是Web应用，
    每次进入ShiroFilter都会自动调用session.touch()来访问最后访问时间。

    6.session.touch() & session.stop():更新会话最后访问时间及销毁会话；当Subject.logout()时
    会自动调用stop方法来销毁会话。
    如果在web中，调用HttpSession.invalidate()也会自动调用Shiro Session.stop方法进行销毁Shiro的
    会话。

    7.session.setAttribute(key, val)
      session.getAttribute(key)
      session.removeAttribute(key)
      设置/获取/删除会话属性，在整个会话范围内都可以对这些属性进行操作。

      在controller层可以使用HttpSession，在service层可以使用Shiro的session,
      这样可以及时在service层也可以访问到session数据。

    Shiro提供了会话验证调度器，用于定期的验证是否会话是否已经过期，如果过期将停止会话。
    处于性能考虑，一般情况下都是获取会话时来验证会话是否过期并停止会话的；
    但是如在web环境中，如果用户不主动退出是不知道会话是否过期的，因此需要定期的检测会话是否过期，
    Shiro提供了会话验证调度器，SessionValidateScheduler,
    也提供了使用Quartz会话验证调度器 QuartzSessionValidationScheduler


缓存
    Shiro内部相应的组件DefaultSecurityManager会自动检测相应的对象如Realm是否实现了CacheManagerAware
    并自动注入了相应的CacheManager，如果自动注入了的话，该对象就可以使用缓存了。

   Shiro提供了CachingRealm，其实现了CacheManagerAware接口，提供了缓存的一些基础实现；
   AuthenticatingRealm 及AuthorizationRealm 也分别提供了对AuthenticationInfo和AuthorizationInfo
   信息的缓存。因此可以直接在Realm中使用缓存。

   在实际的项目使用中一般会用redis来做Shiro的缓存。



RememberMe功能
    Shiro提供了记住我的功能，比如你访问如淘宝等一些网站时，关闭了浏览器，下次再打开时还能记住你是谁，下次访问
    时无需在登录即可访问，基本流程如下：
    1.首先在登录页面中选中RememberMe然后登录成功，如果是浏览器登录，一般会把RememberMe的Cookie写入到客户端
      并且保存下来；
    2.关闭浏览器再重新打开，会发现浏览器还是记住你的；
    3.访问一般的网页服务器端还是知道你是谁；
    4.但是比如我们要访问淘宝时，如果要查看订单或者进行支付时，此时还是需要再进行身份认证的，以确保当前用户还是你。


认证与记住我
    。subject.isAuthenticated()表示用户是进行了身份验证登录的，即使用Subject.login()进行了登录
    。subject.isRemembered() 表示用户是通过记住我登录的，此时可能并不是真正的你，有可能是别人在使用你的电脑，也可能
         是你的cookie被窃取了
    一个登录后的用户，调用 subject.isAuthenticated == true或者 subject.isRemembered == true
      不可能同时两者均为true，换言之两种登录方式只能有一种

   访问一般网页： 我们使用user拦截器即可，user拦截器只要用户登录 isRemembered()||isAuthenticated()过即可访问成功
   访问特殊网页： 如提交订单页面，我们使用 authc 拦截我即可，authc 拦截器会判断用户是否是通过subject.login()登录的，
                如果是才会放行，否则会跳转到登录页面叫你重新登录。

    记住我的实现：
    在页面上设置一个checkBox，如果checkBox被选中了，则在controller层中，构建UsernamePasswordToken的时候，
    设置一下usernamePasswordToken.setRememberMe(true)即可。

    可以通过设置SecurityManager的RememberMeManager中cookie的maxAge来设置cookie的时长









Shiro中的拦截器
    Shiro内置了很多默认的拦截器，比如身份验证、授权等相关的。默认拦





  为什么外勤云的时候还在使用shiro处理登录权限校验，等到中台这边就完全使用自己进行权限校验，然后放到token中了呢？两者有什么区别呢？










