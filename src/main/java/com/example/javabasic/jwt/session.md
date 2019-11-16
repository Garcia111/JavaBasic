会话管理

    如何标记唯一的客户？
  1.使用IP地址可以吗？
  
        容器确实可以得到请求的IP地址，但是IP地址并不能够唯一地标识客户。例如，如果你在一个局域网中，你的电脑会有唯一的IP
        地址，但是这个IP地址与外界中的容器看到的IP地址是不同的。对于服务器来说，你的IP地址是路由器的地址，所以你和这个局域网
        中其他人的IP地址是一样的。因此，IP地址不能唯一的标识Internet上的特定客户。
  
  2. 使用HTTPS呢？如果用户登录，而且连接是安全的（HTTPS）,容器就能准确地知道是哪一个客户
  
         如果用户登录，并且连接是安全的。容器就能认出客户，并将它与一个会话关联。但是，这个条件一般不能满足：
         因为：大多数好的网站设计都指出，除非确实有必要，否要不应该要求用户登录；除非确实有意义，否则不要使用HTTPS
        
         如果用户只是想要浏览一下，就算是用户确实向购物车里面增加商品。在用户真正决定结账之前，你可能并不想让系统对
         他们进行身份认证。
         
 
 客户需要一个唯一的会话ID       
       
       对客户的第一个请求，容器会生成一个唯一的会话ID，并且通过响应将其返回给客户。客户在以后的每一个请求中会发回这个会话
       ID，容器看到ID之后，就会找到匹配的会话，并且将这个会话与请求关联。

1.建立会话       
     
    容器与客户使用Cookie交换会话ID信息
     
        容器发回给浏览器的响应头中包含字段：Set-Cookie:JESSIONID = 0AAB6C8DE415
        浏览器再向容器请求时，请求头中包含字段：Cookie: JESSIONID = 0AAB6C8DE415
        
    浏览器与容器交换会话ID的过程 
 
        创建一个会话：服务器需要告诉容器想要创建一个会话，但是除此之外：创建会话ID 创建新的Cookie对象
        将会话ID添加到Cookie对象，将cookie设置为响应的一部分 等工作内容都将由容器负责。
        创建方式：HttpSession session = request.getSession();
        
        使用一个会话：服务器端需要从请求中取得session,容器会从请求中的cookie得到会话ID，将这个会话ID与一个
        现有的会话相匹配，并将回话与当前请求关联。
        使用方式：HttpSession session = request.getSession();
        
        Cookie的所有工作都在后台进行
        IF(请求中包含一个会话ID cookie){
            找到与该ID匹配的会话；
        }ELSE IF（请求中没有会话ID cookie 或者 没有与此会话ID匹配的当前会话）{
            创建一个新的会话；
        }
        
    如果浏览器禁用Cookie，如何使用会话呢？----URL重写
    
        如果客户不接受cookie,服务端不会得到异常，没有警告，没有提示。浏览器不会发回一个带有会话ID cookie首部的请求。
        如果用户不接受cookie，可以使用URL重写来建立会话，URL重写能够取得置于cookie中的会话ID，并将会话ID
        附加到访问应用的各个URL后面。
        
        假设有一个web页面，其中每个链接都有一些额外的信息，【URL;+jessionId = 1234567】附加到URL的最后，当用户点击这种改进后的链接时，到达容器
        的请求会在最后携带这个额外信息，容器会取下来请求URL中这个额外的部分，并用它来查找匹配的会话。
        
   URL重写是自动的，但是只有当你对URL重写完成了编码时，他才会奏效。必须通过response.encodeURL 和 response.encodeRedirectURL 来对所有
   的URL进行编码，其他的所有事情都由容器来完成。
        
        容器总是默认地先使用cookie，而URL重写只作为最后一道防线。容器能够使用URL重写建立会话的前提是【对URL进行显示的编码】:response.encodeURL("URL")
        如果想要在重定向时使用URL重写，可以如下使用：response.encodeRedirectURL(url)
        

2.删除会话
    
    情景：1.客户到来，开始一个会话，然后离开了这个网站；
         2.客户到来，开始一个会话，然后他的浏览器崩溃了；
         3.客户到来，开始一个会话，然后完成了一个购买结账来结束会话
         
         容器是如何知道客户什么时候走的呢？容器又如何知道什么时候能够安全地撤销一个会话？   
         HttpSession接口
         
         1.getCreationTime():返回第一次创建会话的时间；-----可以用来为某些会话的寿命限制为一个固定的时间；
         2.getLastAccessedTime():返回容器最后一次得到包含这个会话ID的请求后过去了多长时间（毫秒数）
            得出客户最后一次访问这个会话是什么时候，可以用来确定客户已经离开了多长时间
         3.setMaxInactiveInternal():指定对于这个会话客户请求的最大间隔时间。
            如果已经过去了指定的时间，客户未对这个会话做任何请求，就会导致会话被撤销。可以使用这个方法减少服务器中无用的会话。
         4.getMaxInactiveInternal()
         5.invalidate():结束会话，当前存储在这个会话中的所有会话属性也会解除绑定。          
        
Cookie与Session
    
   Cookie可以用于其他用处吗？Cookie是不是只能用于会话？
    
    Cookie实际上就是在客户和服务器之间交换的一小段数据，可以使用cookie在服务器和客户之间交换名/值 String对。
    服务器将cookie发送给客户，客户再在以后的每个请求中发回这个cookie。
    客户的浏览器退出时，会话cookie就会消失，但是你可以告诉cookie在客户端上待得更久一些，甚至在浏览器关闭之后还持久保存。
    
    
  与Cookie相关的三个类：HttpServletRequest  HttpServletResponse   Cookie
  
    1.HttpServeletRequest------getCookies()
    2.HttpServeletResponse------addCookie()
    3.javax.servelet.http.Cookie
        Cookie(String,String)
        String getDomain()
        int getMaxAge()
        String getName()
        String getPath()
        boolean getSecure()
        String getValue()
        void setDomain(String)
        void setMaxAge(int)
        void setPath(String)
        void setValue(String)
        
        1.创建一个新的Cookie:Cookie cookie = new Cookie("username",name);
        2.设置cookie在客户端上存活多久  cookie.setMaxAge(30*60);
        3.将cookie发送到客户 response.addCookie(cookie);
        4.从客户请求得到cookie,或者多个cookie
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("username")){
                String userName = cookie.getValue();
                System.out.println("Hello "+userName);
                break;
            }
        }
        
  会话的生命周期事件
    1.创建会话
        容器第一次创建一个会话
    2.撤销会话
        容器设置一个会话无效，或者因为这个会话超时，或者因为应用的某个部分调用了会话的invalidate()
    3.属性相关
        增加一个属性：setAttribute()
        删除一个属性：removeAttribute()
    4.迁移
        容器打算将会话迁移到另外一个JVM中，要在会话移动之前调用，这样就能让属性有机会做好迁移的准备
        
Session迁移
    应用的各个部分可以复制在网络中的多个节点上，容器可能会完成负载均衡，取得客户的请求，将请求发送到多个JVM上。
    这说明，每次客户请求时，最后有可能会到达同一个servlet的不同实例。
    那么在分布式环境中 ServletContext ServletConfig和HttpSession对象会有什么变化?
    
    只有HttpSession对象及其属性会从一个JVM迁移到另外一个JVM，每个JVM中都会有一个ServletContext；
    每个JVM上的每个Servlet都会有一个ServletConfig;
    对于每个Web应用的一个给定的会话ID,只有一个HttpSession对象，不论应用分布在多少个JVM上。
    
  会话的迁移意味着，会话在一个JVM1上钝化，并在另一个JVM2上激活，JVM2会建立一个新线程，将新请求与刚前来的会话相关联。
    
    会话中属性的迁移：
    会话迁移与串行化过程中，容器需要迁移Serializable属性，确保你的SessionAttribute对象类型都是Serializable，
    但是如果属性类型不是Serializable，可以让属性对象类实现HttpSessionActivationListener。
        
        
    区分：
    HttpSessionListener
    HttpSessionAttributeListener
    HttpSessionBindingListener
    HttpSessionActiveListener
  
  
  
  
  
  
  
  
  
  
        
    
    