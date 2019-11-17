package com.example.javabasic.jwt_session_cookie.trade_service;

import lombok.Data;

/**
 * @author：Cheng.
 * @date：Created in 17:46 2019/11/14
 */
@Data
public class JwtConfigs {

    /** 私钥 ID. */
    private String keyId;

    /** 私钥值. */
    private String privateKey;

    /** Access Token 过期时间，单位：分钟 */
    private Integer accessTokenExpiresIn;

    /** Refresh Token 过期时间，单位：分钟 */
    private Integer refreshTokenExpiresIn;

    /** Refresh Token 过期时间的阈值，如果剩余时间小于这个阈值，则会重新生成 refresh token，单位：分钟 */
    private Integer refreshTokenExpiresThreshold;
}
