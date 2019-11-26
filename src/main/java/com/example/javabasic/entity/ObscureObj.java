/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created on 2019-08-14.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode
@Builder
public class ObscureObj implements Serializable {

    private static final long serialVersionUID = -4441777216465606726L;

    /** 日期中的长度. */
    private int length;

    /** 补位字符. */
    private char padChar;

    /** 日期混淆步进长度. */
    private int step;

    /** 当前日期与 20190101 之间的天数差，不足六位左侧补 0. */
    private int days;

    /** 自增序列号，不足六位左侧补 0. */
    private long serial;

    /** 随机字符，可选范围：（1 - 9）. */
    private int random;

    /** 校验值，与随机字符相除的余数. */
    private int checksum;
}
