package com.example.javabasic.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 23:32 2020/2/25
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Role {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;
}
