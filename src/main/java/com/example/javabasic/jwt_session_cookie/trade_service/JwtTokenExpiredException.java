package com.example.javabasic.jwt_session_cookie.trade_service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.joda.time.DateTime;

/**A
 * Created on 2019-08-19.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JwtTokenExpiredException extends BaseRuntimeException {

    private static final long serialVersionUID = 1007316837021738039L;

    private final DateTime validBefore;

    /**
     * Instantiates a new Jwt token expired exception.
     *
     * @param message the message
     * @param validBefore the valid before
     */
    public JwtTokenExpiredException(String message, DateTime validBefore) {
        super(message);
        this.validBefore = validBefore;
    }
}