package com.example.javabasic.jwt.trade_service;

import java.io.Serializable;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 17:30 2019/11/14
 */
public interface UrlWhiteList extends Serializable {

    /**
     * Gets white list.
     *
     * @return the white list
     */
    List<String> getWhiteList();
}