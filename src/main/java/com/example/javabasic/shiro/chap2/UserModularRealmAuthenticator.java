package com.example.javabasic.shiro.chap2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:27 2020/3/6
 *
 * 当配置了多个Realm时，我们通常使用的认证器是shiro自带的org.apache.shiro.authc.pam.ModularRealmAuthenticator，
 * 其中决定使用的Realm的是doAuthenticate()方法
 */
@Slf4j
public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {


    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken){
        log.info("UserModularRealmAuthenticator:method doAuthenticate() execute ");
        // 强制转换回自定义的CustomizedToken
        UserToken userToken = (UserToken) authenticationToken;
        // 登录类型
        String loginType = userToken.getLoginType();
       Collection<Realm> realms = getRealms();
       List<Realm> typeRealms = new ArrayList<>();
       for(Realm realm :realms){
           if(realm.getName().contains(loginType)){
               typeRealms.add(realm);
           }
       }

       if(typeRealms.size()==1){
           log.info("doSingleRealmAuthentication() execute ");
           return doSingleRealmAuthentication(typeRealms.get(0),userToken);
       }else{
           log.info("doMultiRealmAuthentication() execute ");
           return doMultiRealmAuthentication(typeRealms,userToken);
       }

    }


}
