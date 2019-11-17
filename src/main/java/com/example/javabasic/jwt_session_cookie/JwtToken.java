package com.example.javabasic.jwt_session_cookie;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author：Cheng.
 * @date：Created in 16:00 2019/11/14
 */
@Slf4j
@Configuration
public class JwtToken {

    @Value("${jwt_session_cookie.secret}")
    private String secret;

    @Value("${jwt_session_cookie.expire}")
    private long expire;

    /**
     * 更新token
     * @param userId
     * @return
     */
    public String generateToken(Long userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }


    /**
     * 根据token获取token所面向的用户，即jwt分发给的userId
     * @param token
     * @return
     */
    public Claims getClaimByToken(String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }
        String[] header = token.split("Bearer");
        token  = header[1];
        try{
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }catch (Exception e){
            log.debug("validate is token error",e);
            return null;
        }
    }


    /**
     * token 是否过期
     * @param expiration
     * @return
     */
    public static  boolean isTokenExpired(Date expiration){
        return expiration.before(new Date());
    }

}
