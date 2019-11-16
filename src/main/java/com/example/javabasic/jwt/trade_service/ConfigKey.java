package com.example.javabasic.jwt.trade_service;

import lombok.experimental.UtilityClass;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author：Cheng.
 * @date：Created in 17:43 2019/11/14
 */
@UtilityClass
public final class ConfigKey {

    /** The constant PROCESSORS_NUMBER. */
    public static final int PROCESSORS_NUMBER = Runtime.getRuntime().availableProcessors();

    /** The constant REDIS_KEY_SEPARATOR. */
    public static final String REDIS_KEY_SEPARATOR = ":";

    /** The constant DEFAULT_SEARCH_ALL. */
    public static final String DEFAULT_SEARCH_ALL = "0";

    /** The constant DEFAULT_SQL_LIKE. */
    public static final String DEFAULT_SQL_LIKE = "%";

    /** The constant DEFAULT_CHARSET. */
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /** The constant HTTP_HEADER_AUTHORIZATION. */
    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";

    /** The constant JWT_KEY_ACCOUNT_NAME. */
    public static final String JWT_KEY_ACCOUNT_NAME = "accountName";
    /** The constant JWT_KEY_ACCOUNT_TYPE. */
    public static final String JWT_KEY_ACCOUNT_TYPE = "accountType";
    /** The constant JWT_KEY_CLIENT_ID. */
    public static final String JWT_KEY_CLIENT_ID = "clientId";
    /** The constant JWT_KEY_ROLES. */
    public static final String JWT_KEY_ROLES = "roles";
    /** The constant JWT_KEY_AUTHORITIES. */
    public static final String JWT_KEY_AUTHORITIES = "authorities";

    /** The constant JWT_KEY_ISS_VALUE. */
    public static final String JWT_VALUE_ISS = "account-center";
    /** The constant JWT_KEY_AUD_VALUE. */
    public static final String JWT_VALUE_AUD = "ssp-web";

    /** OAUTH 2 授权模式定义. */
    public static final String OAUTH_GRANT_TYPE_PASSWORD = "password";

    /** The constant OAUTH_GRANT_TYPE_AUTH_CODE. */
    public static final String OAUTH_GRANT_TYPE_AUTH_CODE = "authorization_code";

    /** The constant OAUTH_GRANT_TYPE_CLIENT_CREDENTIALS. */
    public static final String OAUTH_GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";

    /** The constant OAUTH_GRANT_TYPE_IMPLICIT. */
    public static final String OAUTH_GRANT_TYPE_IMPLICIT = "implicit";

    /** The constant OAUTH_GRANT_TYPE_REFRESH_TOKEN. */
    public static final String OAUTH_GRANT_TYPE_REFRESH_TOKEN = "refresh_token";

    /** OAUTH 2 作用范围定义. */
    public static final String OAUTH_SCOPE_READ = "read";

    /** The constant OAUTH_SCOPE_WRITE. */
    public static final String OAUTH_SCOPE_WRITE = "write";

    /** The constant OAUTH_SCOPE_READ_WRITE. */
    public static final String OAUTH_SCOPE_READ_WRITE = "read_write";

    /** The constant TOKEN_TYPE_BEARER. */
    public static final String TOKEN_TYPE_BEARER = "Bearer";

    /** The constant MIN_PAGE. */
    public static final int MIN_PAGE = 1;

    /** The constant MIN_SIZE. */
    public static final int MIN_SIZE = 1;

    /** The constant MAX_SIZE. */
    public static final int MAX_SIZE = 40;

    /** The constant DEFAULT_PAGE_START. */
    public static final int DEFAULT_PAGE_START = 0;

    /** The constant DEFAULT_PAGE_LIMIT. */
    public static final int DEFAULT_PAGE_LIMIT = 20;
}

