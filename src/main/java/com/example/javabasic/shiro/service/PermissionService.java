package com.example.javabasic.shiro.service;

import com.example.javabasic.shiro.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 23:29 2020/3/4
 */
@Service
public class PermissionService {

    public Set<String> queryPermissionsByUserId(Long userId){
        return null;
    }

    public List<Permission> findPermissionByAdmin(Long adminId){return null;}

}
