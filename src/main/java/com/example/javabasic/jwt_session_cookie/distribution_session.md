分布式Session
    
    Session是服务器用来保存用户操作的一系列会话信息，由Web容器进行管理。单机情况下，不存在Session共享的情况。
    分布式情况下，如果不进行Session共享会出现请求落到不同机器，要重复登录的情况。
    一般来说，解决Session共享有以下几种方案：
  
  1.Session复制
    Session复制是早期的企业级的使用比较多的一种服务器集群Session管理机制，应用服务器开启Web容器的
    Session复制功能，在集群中的几台服务器之间同步session对象，使得每台服务器上都保存所有的session
    信息，这样任何一台宕机都不会导致session的数据丢失，服务器使用session时，直接从本地获取。
    
    这种方式的缺点：
    在应用集群达到数千台的时候，就会出现瓶颈，每台都需要备份session，出现内存不够用的情况。
    
  2.Session绑定（也称为粘性session）
    利用hash算法，必须nigix的ip_hash，使得同一个客户的请求分发到同一台服务器上。简单来讲就是nigix将
    同一个客户的所有请求都分发的同一台服务器上。
    
    这种方式不符合对系统的高可用要求，因为一旦某台服务器宕机，那么该机器上的session也就不复存在了，
    用户请求切换到其他机器后没有session，无法完成业务处理。
    
  3.利用cookie记录session
    Session记录在客户端，每次请求服务器的时候，将session放在请求中发送给服务器，服务器处理完请求之后，
    再将修改后的session响应给客户端，
    
    缺点：
    利用cookie记录session也有缺点，比如
    1.受cookie大小的限制，能记录的信息有限；
    2.可伪造，可能会被篡改数据信息，安全性不够好。
    
    但是由于cookie简单易用，支持应用服务器的线性伸缩，而大部分要记录的session信息较小，因此很多网站仍在或多或少地
    使用cookie记录session。
    
  
  4.Session集中存储
    session服务器可以解决上面的所有问题，利用独立部署的session服务器（集群），统一管理session，
    服务器每次读写session时，都会访问session服务器。
    **这种解决方案事实上是应用服务器的状态分离：分为无状态的应用服务器和有状态的Session服务器，
    然后针对这两种服务器的不同特性分别设计架构**
    
    对于有状态的session服务器，一种比较简单的方法是利用分布式缓存，数据库等，在这些产品的基础上进行包装。
    使其符合session的存储和访问要求。如果业务场景对session管理有比较高的要求，比如利用session服务基层
    单点登录（sso）用户服务器等功能，需要开发专门的session服务管理平台。
    eg:将session存入分布式缓存集群中的某台服务器上，当用户访问不同节点时，先从缓存中拿Session信息。
    
    其原理是以sessionId作为Key，序列化后的HttpSession对象作为Value存储在Redis中，然后将SessionId返回给客户端。
    当下一次客户端发送HTTP请求到服务器的时候，会带上这个SessionId，服务器再根据SessionId从Redis中拿到相应的Session
    数据并反序列化成HttpSession对象。由于对HttpSession对象进行了几种存储，而不是存储在本地服务器内存，所以
    即使同个用户的多次HTTP请求落到不同的服务器上，也能将SessionId与对应的HttpSession对象关联起来，从而实现
    分布式环境下的Session共享。
    
    使用场景：集群中机器数较多，网络环境复杂
    优点：可靠性好
    缺点：实现复杂，稳定性依赖于缓存的稳定性，当Redis挂了之后，那么所有的请求都要挂了，session都无法获取，
    可以采用类似主备的思想进行解决。
    
    
    //todo 自己实现一把
    
      
    
    
    
    
    