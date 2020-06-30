Shiro

    常用的Java Web访问控制实施流程
        1.用户 向 [权限管理模块] 申请用户 申请权限
        2，用户使用用户名密码 登录， 使用 [安全管控模块] 做登录认证
        2.安全管控模块 向权限管理模块请求获取用户权限数据和用户数据

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
         _可以有一个或者多个Realm，可以认为是安全实体数据源，即用于获取安全实体的，可以是JDBC实现，也可以是内存实现
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







  为什么外勤云的时候还在使用shiro处理登录权限校验，等到中台这边就完全使用自己进行权限校验，然后放到token中了呢？两者有什么区别呢？










