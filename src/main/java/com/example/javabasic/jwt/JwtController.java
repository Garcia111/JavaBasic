package com.example.javabasic.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.Arrays;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 16:19 2019/11/14
 */
@RestController
public class JwtController {

    @Autowired
    private JwtToken jwtToken;


    @PostMapping("/login")
    public String login(@RequestBody User user){
        //1.验证用户名和密码
        Long userId = user.getId();
        String token = jwtToken.generateToken(userId);
        return token;
    }



    @GetMapping("/getUserInfo")
    public String getUserInfo(@RequestHeader("Authorization") String authHeader) throws AuthenticationException {
        // 黑名单token
        List<String> blacklistToken = Arrays.asList("禁止访问的token");
        Claims claims = jwtToken.getClaimByToken(authHeader);
        if (claims == null || JwtToken.isTokenExpired(claims.getExpiration()) || blacklistToken.contains(authHeader)) {
            throw new AuthenticationException("token 不可用");
        }
        String userId = claims.getSubject();
        // 根据用户id获取接口数据返回接口
        return userId;
    }
}
