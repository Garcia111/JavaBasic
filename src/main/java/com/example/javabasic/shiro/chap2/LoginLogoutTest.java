package com.example.javabasic.shiro.chap2;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author：Cheng.
 * @date：Created in 11:02 2020/3/5
 */
@Slf4j
public class LoginLogoutTest {

    @Test
    public void testHelloworld(){
        //1.获取SecurityManager 工厂，此处使用Ini配置文件初始化Security Manager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.得到SecurityManager 实例，并绑定给SecurityUtils,这是一个全局设置，设置一次即可
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.得到Subject及创建用户名/身份验证Token，Subject会自动绑定到当前线程
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("root","123456");

        try{
            //4.登录，及身份验证
            subject.login(token);
        }catch (AuthenticationException e){
            log.info("用户名或者密码不正确，"+e.getMessage());
        }
        //5.断言用户已经登录
        Assert.assertEquals(true,subject.isAuthenticated());

        //6.退出
        subject.logout();
     }

}
