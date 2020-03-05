package com.example.javabasic.shiro.permission_example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author：Cheng.
 * @date：Created in 22:11 2020/3/4
 */
public interface UserDao implements JpaRepository<User,Long> {
}
