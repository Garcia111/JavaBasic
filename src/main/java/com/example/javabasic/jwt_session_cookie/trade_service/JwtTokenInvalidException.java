package com.example.javabasic.jwt_session_cookie.trade_service;

import cn.com.ctsi.commons.exception.BaseRuntimeException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created on 2019-08-19.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JwtTokenInvalidException extends BaseRuntimeException {

    private static final long serialVersionUID = 7289486845590529105L;

    private final String invalidToken;

    /**
     * Instantiates a new Jwt token invalid exception.
     *
     * @param message the message
     */
    public JwtTokenInvalidException(String message) {
        super(message);

        this.invalidToken = null;
    }

    /**
     * Instantiates a new Jwt token invalid exception.
     *
     * @param message the message
     * @param cause the cause
     * @param invalidToken the invalid token
     */
    public JwtTokenInvalidException(String message, Throwable cause, String invalidToken) {
        super(message, cause);

        this.invalidToken = invalidToken;
    }
}

