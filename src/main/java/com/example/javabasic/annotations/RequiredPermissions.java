package com.example.javabasic.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解标识访问某个接口需要用户或有的权限
 * @author：Cheng.
 * @since： 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequiredPermissions {

    String[] value() default {};
}
