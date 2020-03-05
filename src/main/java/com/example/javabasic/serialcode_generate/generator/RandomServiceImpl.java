///*
// * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
// * All rights reserved.
// */
//package com.example.javabasic.serialcode.generator;
//
//import com.example.javabasic.com.example.javabasic.shiro.example1.entity.ObscureObj;
//import com.google.common.base.Strings;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.text.RandomStringGenerator;
//import org.springframework.stereotype.Service;
//
//
//@Slf4j
//@Service
//public class RandomServiceImpl implements RandomService {
//
//    /** 指定随机字符串的字符选择范围. */
//    private static final char[][] PAIRS = {{'A', 'Z'}, {'0', '9'}};
//
//    private static final char[][] PAIRS_FOR_OBSCURE = {{'1', '9'}};
//
//    @Override
//    public String random(int length) {
//        return new RandomStringGenerator.Builder().withinRange(PAIRS).build().generate(length);
//    }
//
//    @Override
//    public String obscure(ObscureObj value) {
//        int length = value.getLength();
//        char padChar = value.getPadChar();
//
//        String originDays = Strings.padStart(String.valueOf(value.getDays()), length, padChar);
//        String originSerial = Strings.padStart(String.valueOf(value.getSerial()), length, padChar);
//        String reversedSerial = StringUtils.reverse(originSerial);
//
//        String random =
//                new RandomStringGenerator.Builder()
//                        .withinRange(PAIRS_FOR_OBSCURE)
//                        .build()
//                        .generate(1);
//
//        return originDays + reversedSerial + random;
//    }
//
//}
