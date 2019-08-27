package com.example.spring.random;

import org.apache.commons.text.RandomStringGenerator;

/**
 * @author：Cheng.
 * @date：Created in 16:58 2019/8/27
 */
public class RandomGeneratorUtil {


    /** 指定随机字符串的字符选择范围. */
    private static final char[][] PAIRS = {{'A', 'Z'}, {'0', '9'}};

    private static final char[][] PAIRS_FOR_OBSCURE = {{'1', '9'}};

    public static void main(String[] args){
        String randomStr = new RandomStringGenerator.Builder().withinRange(PAIRS).build().generate(8);
        System.out.println("随机字符串："+randomStr);
    }
}
