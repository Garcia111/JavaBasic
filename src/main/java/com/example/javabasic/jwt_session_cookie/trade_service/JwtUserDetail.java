package com.example.javabasic.jwt_session_cookie.trade_service;

import java.io.Serializable;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created on 2019/9/23.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Data
@Builder
@ToString
@EqualsAndHashCode
public class JwtUserDetail implements Serializable {

    private static final long serialVersionUID = 7114197425703813641L;

    /** 账户 ID. */
    private Long accountId;

    /** 账户名.. */
    private String accountName;

    /** 账户类型. */
    private Integer accountType;

    /** 账户角色列表. */
    private List<String> roles;

    /** 账户权限列表. */
    private List<String> permissions;

    /** Token ID. */
    private String tokenId;

    /** 客户端 ID. */
    private String clientId;
}