package com.example.javabasic.shiro.realm;


import com.example.javabasic.shiro.entity.Permission;
import com.example.javabasic.shiro.entity.Role;
import com.example.javabasic.shiro.entity.User;
import com.example.javabasic.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author：Cheng.
 * @date：Created in 23:35 2020/2/25
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String)principalCollection.getPrimaryPrincipal();

        User user = userService.getUserByName(name);

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Role role: user.getRoles()){
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());

            //添加权限
            for(Permission permission : role.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(permission.getPermissionsName());
            }

        }
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //在Post请求时会先进行认证
        if(authenticationToken.getPrincipal() == null){
            return null;
        }

        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = userService.getUserByName(name);
        if(user == null){
            return null;
        }else{
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword(),getName());
            return simpleAuthenticationInfo;
        }
    }
}
