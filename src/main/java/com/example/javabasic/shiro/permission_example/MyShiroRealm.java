/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.shiro.permission_example;


import com.example.javabasic.shiro.entity.User;
import com.example.javabasic.shiro.service.PermissionService;
import com.example.javabasic.shiro.service.RoleService;
import com.example.javabasic.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;


    /**
     * 鉴权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获取登录用户名
        String name = (String) principals.getPrimaryPrincipal();
        log.info(
                "权限配置-->MyShiroRealm.doGetAuthorizationInfo() name：" + name,
                "",
                MyShiroRealm.class);
        User user = userService.getUserByName(name);
        //判断当前登录用户是否存在
        if (Objects.isNull(user)) {
            throw new RuntimeException("LoginUserRealm doGetAuthorizationInfo， user is null");
        }
        //设置当前登录用户的角色
        authorizationInfo.setRoles(roleService.queryUserRole(user.getId()));
        //设置当前登录用户的权限
        Set<String> stringPermissions = permissionService.queryPermissionsByUserId(user.getId());
        authorizationInfo.setStringPermissions(stringPermissions);
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        //校验账号密码是否正确
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        List<User> users = userService.getUserByNameAndPassword(name, password);
        if (users.isEmpty()) {
            throw new AccountException("帐号或密码不正确！");
        }

//        MD5 md5 = new MD5();
//        SimpleAuthenticationInfo simpleAuthenticationInfo =
//                new SimpleAuthenticationInfo(name, "",md5.decode(password), getName());
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(name, "MD5解密后的密码", getName());
        return simpleAuthenticationInfo;
    }
}
