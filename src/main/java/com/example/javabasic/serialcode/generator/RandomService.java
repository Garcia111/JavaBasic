package com.example.javabasic.serialcode.generator;

import com.example.javabasic.entity.ObscureObj;

/**
 * @author：Cheng.
 * @date：Created in 10:29 2019/11/26
 */
public interface RandomService {

    /**
     * 生成指定长度的随机字符串
     * @param length
     * @return
     */
    String random(int length);


    /**
     * 获取混淆字符串
     * @param value
     * @return
     */
    String obscure(ObscureObj value);


}
