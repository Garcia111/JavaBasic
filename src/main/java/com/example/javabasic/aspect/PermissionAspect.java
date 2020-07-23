package com.example.javabasic.aspect;



import com.example.javabasic.annotations.RequiredPermissions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Cheng.
 * @since： 1.0.0
 */
@Slf4j
@Aspect
@Component
public class PermissionAspect {

    private List<String> getUserPermissions(){
        log.info("获取用户的权限");
        return Lists.newArrayList("ROLE_ADMIN");
    }

    private List<String> getUserRoles(){
        log.info("获取用户角色");
        return Lists.newArrayList("ADMIN");
    }


    //注解中切点的注解和传入增强方法中的注解的参数名称需要保持统一
    @Before(value = "@annotation(requiredPermissions)")
    public void beforeMethod(RequiredPermissions requiredPermissions){
        //获取访问接口需要的权限
        ArrayList<String> list = Lists.newArrayList(requiredPermissions.value());
        validateUserPermission(list);
        System.out.println("method start time "+System.currentTimeMillis());
    }


    //注解中切点的注解和传入增强方法中的注解的参数名称需要保持统一
    @Before(value = "@annotation(requiredRoles)")
    public void beforeMethod(RequiresRoles requiredRoles){
        //获取访问接口需要的权限
        ArrayList<String> list = Lists.newArrayList(requiredRoles.value());
        validateUserRoles(list);
        System.out.println("method start time "+System.currentTimeMillis());
    }

    private void validateUserPermission(List<String> permissions){
        if(CollectionUtils.isEmpty(permissions)){
            System.out.println("不需要权限，直接放行");
            return;
        }

        //获取用户权限，判断是否包含访问接口所需要的权限
        List<String> userPermissions = getUserPermissions();
        if(!userPermissions.containsAll(permissions)){
            throw new RuntimeException("没有权限，禁止访问");
        }
    }


    /**
     *
     * @param roles 可以访问接口的角色
     */
    private void validateUserRoles(List<String> roles){
        if(CollectionUtils.isEmpty(roles)){
            System.out.println("任何角色都可以访问接口，放行");
            return;
        }

        //获取用户权限，判断是否包含访问接口所需要的权限
        List<String> userRoles = getUserRoles();
        if(!userRoles.containsAll(roles)){
            throw new RuntimeException("登录用户的角色不能访问此接口");
        }
    }
}














