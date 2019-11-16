package com.example.javabasic.jwt.trade_service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:30 2019/11/14
 */
@Data
@ToString
@EqualsAndHashCode
@ConfigurationProperties(prefix = "gateway.security")
public class SecurityConfigs implements UrlWhiteList {

    private static final long serialVersionUID = -1738879471781763963L;

    /** 白名单列表. */
    private List<String> whiteList;
}
