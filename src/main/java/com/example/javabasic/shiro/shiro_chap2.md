身份认证
    在shiro中，用户需要提供principals（身份）和credentials(证明)给shiro，从而应用能够验证用户身份。

    principals:身份，即主体的标识属性，如用户名 邮箱等，唯一即可。
    credentials:证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
    最常见的principals和credentials组合就是 用户名/密码组合。

    Realm：Shiro从Realm中获取安全数据（如用户、角色、权限）就是说SecurityManager也要验证用户身份，
    那么Shiro需要从Realm获取响应的用户进行比较以确定用户身份是否合法，也需要从Realm中得到用户相应的角色/权限
    进行验证用户是否能进行操作，可以把Realm看成DataSource，即安全数据源。

  SecurityManager可以指定多个Realm，并且会按照realms指定的顺序进行身份认证。
  SecurityManager接口继承了Authenticator，另外还有一个ModularRealmAuthenticator实现，其委托给多个Realm进行验证，
  验证规则通过AuthenticationStrategy接口执行，默认提供的实现：
  1.FirstSuccessfulStrategy:
        只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
  2.AtLeastOneSuccessfulStrategy
        只要有一个Realm验证成功即可，返回所有Realm身份验证成功的信息
  3.AllSuccessfulStrategy
        所有Realm验证成功才算成功，且返回所有Realm身份验证成功的信息，如果有一个失败就失败了。
















