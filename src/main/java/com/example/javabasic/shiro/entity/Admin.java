package com.example.javabasic.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;
    //管理员编号（自增长）
    private Long adminId;
    //管理员用户名
    private String adminName;
    //真实姓名
    private String name;
    //密码
    private String password;
    //联系方式
    private String phone;
    //状态 1：正常 2：冻结
    private Integer isAvalible;
    //权限
    private List<Permission> permissionList;

}
