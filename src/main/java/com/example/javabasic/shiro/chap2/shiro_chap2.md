身份认证
    在shiro中，用户需要提供principals（身份）和credentials(证明)给shiro，从而应用能够验证用户身份。

    principals:身份，即主体的标识属性，如用户名 邮箱等，唯一即可。
    credentials:证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
    最常见的principals和credentials组合就是 用户名/密码组合。

