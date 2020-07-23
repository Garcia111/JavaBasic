package com.example.javabasic.controller;


import com.example.javabasic.shiro.example1.LoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author：Cheng.
 * @date：Created in 20:18 2020/2/26
 */
@Api(value = "/shiro/login",tags = "shiro登录相关接口")
@RequestMapping("/shiro/login")
@RestController
public class LoginController {


    @ApiOperation(value = "1.登录接口",httpMethod = "GET")
    @ApiResponse(code = 200, message = "请求成功")
    @GetMapping
    public String login(LoginRequest loginRequest){
        //获取当前的登录对象
        Subject subject = SecurityUtils.getSubject();
        //判断对象是都已经经过验证
        if(!subject.isAuthenticated()){
            //构造一个UsernamePasswordToken
            UsernamePasswordToken  usernamePasswordToken = new UsernamePasswordToken(
                    loginRequest.getUserName(),
                    loginRequest.getPassword()
            );

         try{
                //进行登录
                subject.login(usernamePasswordToken);
            }catch (AuthenticationException e){
                e.printStackTrace();
                return "账号或密码错误";
            }catch (AuthorizationException e){
                e.printStackTrace();
                return "没有权限";
            }
            return "login success";
        }
        return "already login";
    }

    @RequiresRoles("admin")
    @RequiresPermissions("add")
    @GetMapping("/index")
    public String index(){
        return "index!";
    }




    @RequiresPermissions(value = {"ROLE_ADMIN"})
    @GetMapping("/data/{id}")
    public String  getData(){
        return "your data is ready";
    }

}
