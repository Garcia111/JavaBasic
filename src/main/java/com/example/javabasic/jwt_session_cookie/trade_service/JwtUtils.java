package com.example.javabasic.jwt_session_cookie.trade_service;

/**
 * @author：Cheng.
 * @date：Created in 17:35 2019/11/14
 */

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.security.interfaces.ECPublicKey;
import java.text.ParseException;
import java.util.Date;
import lombok.experimental.UtilityClass;
import org.joda.time.DateTime;

/**
 * Created on 2019-08-20.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@UtilityClass
public class JwtUtils {

    /**
     * Expiration time date time.
     *
     * @param claims the claims
     * @return the date time
     */
    public static DateTime expirationTime(JWTClaimsSet claims) {
        Date expiresClaim = claims.getExpirationTime();
        if (expiresClaim == null) {
            return null;
        }

        return new DateTime(claims.getExpirationTime());
    }
    /**
     * Check expired boolean.
     *
     * @param claims the claims
     * @return the short
     */
    public static long checkExpired(JWTClaimsSet claims) {
        DateTime expiresIn = expirationTime(claims);
        if (expiresIn == null) {
            return -1L;
        }

        return (expiresIn.getMillis() - System.currentTimeMillis()) / (1000 * 60) + 1;
    }

    /**
     * Check jwt_session_cookie token jwt_session_cookie claims set.
     *
     * @param publicKey the public key
     * @param token the token
     * @return the jwt_session_cookie claims set
     */
    public static JWTClaimsSet checkJwtToken(ECPublicKey publicKey, String token) throws JwtTokenInvalidException, JwtTokenExpiredException {
        SignedJWT jwsObject;
        try {
            jwsObject = SignedJWT.parse(token);
            jwsObject.verify(new ECDSAVerifier(publicKey));

            JWTClaimsSet set = jwsObject.getJWTClaimsSet();
            DateTime expired = new DateTime(set.getExpirationTime());
            if (expired.isBeforeNow()) {
                throw new JwtTokenExpiredException("Jwt Token 已过期", expired);
            }

            return set;
        } catch (ParseException | JOSEException e) {
            throw new JwtTokenInvalidException("Jwt Token 解析失败", e, token);
        }
    }
}

