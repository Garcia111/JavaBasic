package com.example.javabasic.shiro.example1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author：Cheng.
 * @date：Created in 22:20 2020/3/4
 */
@Data
@ApiModel(value = "登录请求")
public class LoginRequest {

    @ApiModelProperty(value = "用户名",required = true,dataType = "String")
    private String userName;

    @ApiModelProperty(value = "密码",required = true,dataType = "String")
    private String password;
}
