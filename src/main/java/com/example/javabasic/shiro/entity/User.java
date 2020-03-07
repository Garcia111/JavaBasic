package com.example.javabasic.shiro.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 23:32 2020/2/25
 */

@ApiModel(value = "登录用户")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User {


    private Long id;


    private String userName;


    private String password;

    private Set<Role> roles;


    public Set<String> getPermissions(){
        Set<Permission> permissions = new HashSet<>();
        roles.forEach(role -> {permissions.addAll(role.getPermissions());});

        Set<String> permissionNames = new HashSet<>();
        permissions.forEach(permission -> {permissionNames.add(permission.getPermissionsName());});
        return permissionNames;
    }
}
