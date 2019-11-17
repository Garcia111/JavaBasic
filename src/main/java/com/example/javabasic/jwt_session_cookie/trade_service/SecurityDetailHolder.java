package com.example.javabasic.jwt_session_cookie.trade_service;

import java.util.Optional;
import lombok.experimental.UtilityClass;

/**
 * Created on 2019/9/19.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@UtilityClass
public class SecurityDetailHolder {

    private final ThreadLocal<JwtUserDetail> context = new ThreadLocal<>();

    /**
     * Gets context.
     *
     * @return the context
     */
    public static Optional<JwtUserDetail> getContext() {
        return Optional.ofNullable(context.get());
    }

    /**
     * Sets context.
     *
     * @param value the value
     */
    public static void setContext(JwtUserDetail value) {
        context.set(value);
    }

    /** Remove. */
    public static void remove() {
        context.remove();
    }
}
