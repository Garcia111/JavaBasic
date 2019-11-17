package com.example.javabasic.jwt_session_cookie.trade_service;


import static com.example.javabasic.jwt_session_cookie.trade_service.ConfigKey.*;
import com.nimbusds.jwt.JWTClaimsSet;
import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.ECPublicKey;
import java.text.ParseException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created on 2019-08-19.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableConfigurationProperties(SecurityConfigs.class)
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final KeyPair keyPair;
    private final SecurityConfigs securityConfigs;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        List<String> whiteList = securityConfigs.getWhiteList();
        String requestUri = request.getRequestURI();
        if (requestUri == null || whiteList.contains(requestUri)) {
            log.info("请求的 URL [{}] 不需要提供 Access Token 值", requestUri);
        } else {
            String token = getJwtTokenFromRequest(request);

            if (token != null) {
                JWTClaimsSet set = this.checkJwtToken(token);
                JwtUserDetail user = null;
                try {
                    user =
                            JwtUserDetail.builder()
                                    .accountId(Long.valueOf(set.getSubject()))
                                    .accountName(set.getStringClaim(JWT_KEY_ACCOUNT_NAME))
                                    .accountType(set.getIntegerClaim(JWT_KEY_ACCOUNT_TYPE))
                                    .clientId(set.getStringClaim(JWT_KEY_CLIENT_ID))
                                    .tokenId(set.getJWTID())
                                    .roles(set.getStringListClaim(JWT_KEY_ROLES))
                                    .permissions(set.getStringListClaim(JWT_KEY_AUTHORITIES))
                                    .build();
                } catch (ParseException e) {
                    log.error("获取 Access Token 中的账户信息失败", e);
                }

                if (user != null) {
                    log.info("Access Token 校验成功，组装权限对象");
                    SecurityDetailHolder.setContext(user);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Get Bear jwt_session_cookie from request header Authorization.
     *
     * @param request servlet request.
     * @return token or null.
     */
    private String getJwtTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String bearer = "Bearer ";
        if (token != null && token.startsWith(bearer)) {
            return token.substring(bearer.length());
        }

        return null;
    }

    private JWTClaimsSet checkJwtToken(String token) {
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        return JwtUtils.checkJwtToken(publicKey, token);
    }
}
