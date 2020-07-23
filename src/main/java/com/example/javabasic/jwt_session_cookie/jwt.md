SpringBoot 集成Json Web Token

一、认证
    
   1.1 传统的session认证
        
            http协议是一种无状态协议，即浏览器发送请求到服务器，服务器不知道这个请求是哪个用户发过来的，为了让服务器知道
            是哪个用户发过来的，需要让用户提供用户名和密码进行验证。
            1》当浏览器第一访问服务器（假设是登录接口）服务器验证用户名和密码之后，server会生成一个sessionId(只有第一次会生成，
            其他会使用同一个sessionId),并将该session与用户信息关联起来，然后将sessionId返回给浏览器。
            2》浏览器收到sessionId之后，保存到Cookie中，当用户第二次访问服务器就会携带Cookie值，服务器获取到Cookie值，
            进而获取到sessionId，根据sessionId获取关联的用户信息。
            
   Session的缺点：
           
            1.内存开销:每个用户经过应用认证之后，应用都需要在服务端做一次记录，以方便用户下次请求的鉴别，通常而言session
            都是【保存在内存中，而随着认证用户的增多，服务端的开销会明显增大】。
            2.【分布式扩展性不够】：用户认证之后，服务端做认证记录，如果认证的记录被保存在内存中的话，这意味着用户下次请求还必须请求在这台服务器上
            这样才能拿到授权的资源，这样在分布式的应用上，相应的限制了负载均衡器的能力。这也意味着限制了应用的扩展能力，不能单点登录
            单点登录！！！！
            3.CSRF：因为是基于cookie来进行用户识别的，cookie如果被截获，用户就很容易受到跨站请求伪造的攻击。
            
            
   1.2 基于Token的认证
        
        token原理：
        1.使用用户名和密码请求登录接口；
        2.登录接口验证用户名和密码；
        3.登录接口生成一个uuid作为token，将用户信息作为值，然后保存到redis缓存中jedis.set(token,user);
        4.登录接口返回用户信息和token;
        5.浏览器将token保存到本地；
        6.当请求其他接口时，就携带token值
        7.接口根据token去缓存中查，如果找到了就调用接口，如果找不到就报token错误（一般通过拦截器来实现检查）
        
   session与token的区别：
        
        此种方式原理上和session方式差不多，都是客户端调用接口时携带一个值，服务器通过该值来获取用户的信息。
        不同：
        1.session是将信息保存到本机内存中，对负载均衡有限制(只能负载到同一台机器)，
          【token是保存到缓存服务器(redis)中，对负载均衡没有限制，如果使用同一个redis服务器还可以保证单点登录】。
        2.session一般用在PC上，token即可用在PC上也可以用在APP上。
        
        
   1.3 JWT
        
   简介：
        
        JSON Web Token（JWT）是为了在网络应用环境间传递声明而执行的一种基于JSON的开放标准（(RFC 7519)，
        它定义了一种紧凑（Compact）且自包含（Self-contained）的方式，**用于在各方之间以JSON对象安全传输信息**。
        这些信息可以通过【数字签名】进行验证和信任。可以使用秘密（使用HMAC算法）或使用RSA的公钥/私钥对对JWT进行签名。
        【JWT的声明一般被用来在身份提供者和服务提供者间传递被认证的用户身份信息，以便于从资源服务器获取资源，】
        也可以增加一些额外的其它业务逻辑所必须的声明信息，该token也可直接被用于认证，也可被加密。是目前最流行的跨域认证解决方案。
        
        Compact（紧凑）：由于它们尺寸较小，JWT可以通过URL，POST参数或HTTP标头内发送。另外，尺寸越小意味着传输速度越快。
        Self-contained（自包含）: 有效载荷（Playload）包含有关用户的所有必需信息，避免了多次查询数据库。（我们的应用中token中包含了用户id 角色 name，避免了查库）
        
   应用场景：
   
        1.Authentication（鉴权）：这是使用JWT最常见的情况。一旦用户登录，每个后续请求都将包含JWT，允许用户访问该令牌允许的路由，服务和资源。
                                单点登录是当今广泛使用JWT的一项功能，因为它的开销很小，并且能够轻松地跨不同域使用。
        2.分布式站点的单点登录
        3.信息交换： Json Web Tokens 是在各方之间安全传输信息的好方式。因为【JWT可以签名：例如使用公钥/私钥对，所以可以确定发件人是他们自称的人。】
                    此外，由于使用标头和有效载荷计算签名，因此您还可以验证内容是否未被篡改。
        
   语法
        
        jwt有3个组成部分，每个部分通过点号来分割 header.payload.signature
        1.头部 header：是一个Json对象，描述JWT的元数据。
        2.载荷 payload: 是一个Json对象，用来存放实际需要传递的数据。
        3.签证 signature: 对header和payload使用秘钥进行签名，防止数据篡改
        
        **头部header**
        
        Jwt的头部是一个Json，然后使用Base64URL编码，承载两部分信息：
        1.声明类型typ，表示这个令牌（token）的类型（type），JWT令牌统一写为JWT
        2.声明加密的算法alg，通常直接使用HMACSHA256，就是HS256了，
            也可以使用RSA,支持很多算法(HS256、HS384、HS512、RS256、RS384、RS512、ES256、ES384、ES512、PS256、PS384)
        var header = Base64URL({ "alg": "HS256", "typ": "JWT"})

        Base64URL：Header 和 Payload 串型化的算法是 Base64URL。这个算法跟 Base64 算法基本类似，但有一些小的不同。
        JWT 作为一个令牌（token），有些场合可能会放到 URL（比如 api.example.com/?token=xxx）。Base64 有三个字符+、/和=，
        在 URL 里面有特殊含义，所以要被替换掉：=被省略、+替换成-，/替换成_ 。这就是 Base64URL 算法。
        
        **载荷payload**
        
        payload也是一个JSON字符串，是承载消息具体内容的地方，也需要使用Base64URL编码，
        payload中可以包含预定义的7个可用，它们不是强制性的，但推荐使用，也可以添加任意自定义的key。
        
        1.iss(issuer): jwt签发者
        2.sub(subject): jwt所面向的用户
        3.aud(audience): 接收jwt的一方, 受众
        4.exp(expiration time): jwt的过期时间，这个过期时间必须要大于签发时间
        5.nbf(Not Before): 生效时间，定义在什么时间之前.
        6.iat(Issued At): jwt的签发时间
        7.jti(JWT ID): jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
        
        var payload = Base64URL( {"sub": "1234567890", "name": "John Doe", "iat": 1516239022})
    
        注意 【JWT中payload是不加密的，只是Base64URL编码一下，任何人都可以进行解码，所以不能将敏感信息放在payload中】。
   
   
        **签名Signature**
        
        Signature 部分是对前两部分的签名，防止数据篡改。 
        
        
        var header = Base64URL({ "alg": "HS256", "typ": "JWT"});
        var payload = Base64URL( {"sub": "1234567890", "name": "John Doe", "iat": 1516239022});
        var secret = "私钥";
        var signature = HMACSHA256(header + "." + payload, secret);
        var jwt = header + "." + payload + "." + signature;
        
        
        注意：secret是保存在服务器端的，jwt的签发生成也是在服务器端的，secret就是用来进行jwt的签发和jwt的验证，
        所以，它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        
        
   JWT的特点：
        
        1.因为json的通用性，所以JWT是可以进行跨语言支持的，像JAVA,JavaScript,NodeJS,PHP等很多语言都可以使用。
        2.因为有了payload部分，所以JWT可以在自身存储一些其他业务逻辑所必要的非敏感信息。
        3.它不需要在服务端保存会话信息, 所以它易于应用的扩展。
        4.【Jwt默认是不加密，但是也可以是加密的，就是生成原始的Token之后，可以用秘钥再加密一次】。
        5.JWT 不仅可以用于认证，也可以用于交换信息。有效使用 JWT，可以降低服务器查询数据库的次数。
        缺点
        1.由于服务器不保存 session 状态，因此无法在使用过程中废止某个 token，或者更改 token 的权限。
          也就是说，一旦 JWT 签发了，在到期之前就会始终有效，除非服务器部署额外的逻辑。
        2.JWT 本身包含了认证信息，一旦泄露，任何人都可以获得该令牌的所有权限。---中间攻击
          为了减少盗用，JWT 的有效期应该设置得比较短。对于一些比较重要的权限，使用时应该再次对用户进行认证。
        3. 为了减少盗用，JWT 不应该使用 HTTP 协议明码传输，要使用 HTTPS 协议传输，防止【中间攻击】，对于JWT的有效时间最好设置的短一点
         
         
         JWT的优点：

            1.体积小，因而传输速度更快
            2.多样化的传输方式，可以通过URL传输、POST传输、请求头Header传输（常用）
            3.简单方便，服务端拿到jwt后无需再次查询数据库校验token可用性，也无需进行redis缓存校验，
              只需要对header和payload用私钥加密hash一下，判断与signature是否一致即可，相当于使用计算代替了存储
            4.在分布式系统中，很好地解决了单点登录问题
            5.很方便的解决了跨域授权问题，因为跨域无法共享cookie
            
         JWT的缺点：
            1.因为JWT是无状态的，因此服务端无法控制已经生成的Token失效（也就是即使token被泄露，在token有效期到期时间之前，这个token仍然一致是有效地，不能手动失效），
              是不可控的，这一点对于是否使用jwt是需要重点考量的
            2.获取到jwt也就拥有了登录权限，因此jwt是不可泄露的，网站最好使用https，防止中间攻击偷取jwt
            3.在退出登录 / 修改密码时怎样实现JWT Token失效https://segmentfault.com/q/1010000010043871
            
            
         JWT的安全性
            JWT被确实存在被窃取的问题，但是如果能得到别人的token，其实也就相当于能窃取别人的密码，这其实已经不是JWT安全性的问题。
            网络是存在多种不安全性的，对于传统的session登录的方式，如果别人能窃取登录后的sessionID，也就能模拟登录状态，这和JWT是类似的。
            为了安全，https加密非常有必要，对于JWT有效时间最好设置短一点。
            
            1.JWT安全吗？
                BASE64编码方式是可逆的，也就是透过编码后发放的Token内容是可以被解析的。一般而言，我们都不建议在有效载荷内放置敏感信息，比如使用者的密码。
                
            2.JWT payload的内容可以被伪造吗？
                不能，因为signature是header和payload拼接之后，使用服务器端私钥进行数字签名过的，通过signature可以验证payload的内容是否被伪造。
                
            3.如果我的 Cookie 被窃取了，那不就表示第三方可以做 CSRF 攻击?
                是的，Cookie丢失，就表示身份就可以被伪造。故官方建议的使用方式是存放在LocalStorage中，并放在请求头中发送。？？？？？
                
            4.空间及长度问题
                JWT Token通常长度不会太小，特别是Stateless JWT Token，把所有的数据都编在Token里，很快的就会超过Cookie的大小（4K）或者是URL长度限制。
                什么是Stateless JWT Token？？？
                
            5.Token失效问题
                无状态JWT令牌（Stateless JWT Token）发放出去之后，不能通过服务器端让令牌失效，必须等到过期时间过才会失去效用。
                假设在这之间Token被拦截，或者有权限管理身份的差异造成授权Scope修改，都不能阻止发出去的Token失效并要求使用者请求新的Token。即无法防止中间攻击。
                
         JWT使用建议
            1.Payload中的有效时间不要设置过长
            2.开启Only Http预防XSS攻击，Cookie由服务端来写并将httpOnly设置为true，Cookie中设置了HTTP Only属性，name通过程序（JS脚本 Applet等）
         
         
         JWT 使用
         一般是在请求头里加入Authorization，并加上Bearer标注：
         // Authorization: Bearer <token>
        getToken('api/user/1', {
        headers: {
            'Authorization': 'Bearer ' + token
            }
        })
        
        
        io.jsonwebtoken是最常用的工具包。
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt</artifactId>
            <version>0.9.1</version>
        </dependency>


