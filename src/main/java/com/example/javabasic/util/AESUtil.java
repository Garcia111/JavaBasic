package com.example.javabasic.util;


import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化；
 */
@Slf4j
public class AESUtil {

    /*
     * 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    private static final String sKey = "com.ctsi.mts.app";//key，可自行修改(key需要为16位)
    private static final String ivParameter = "3420038203627869";//偏移量,可自行修改
    private static final int KEY = 379757979;//用于简单加密数字（值）


    // 加密
    public static String encrypt(String sSrc) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
        return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码。
    }

    // 解密
    public static String decrypt(String sSrc,String Key) throws Exception {
        try {
            byte[] raw = Key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            log.error("解密身份证号失败",null,ex);
            return null;
        }
    }


    //简单加密
    public static String simpleEncrypt(String IDCard) {

        long number = Long.valueOf(IDCard.substring(0, 17));
        String last = String.valueOf(IDCard.charAt(IDCard.length() - 1));
        System.out.println(number);
        long cipherText = encrypt(number, KEY);
        return String.valueOf(cipherText) + last;

    }

    //简单解密
    public static String simpleDecrypt(String IDCard) {

        long number = Long.valueOf(IDCard.substring(0, 17));
        String last = String.valueOf(IDCard.charAt(IDCard.length() - 1));
        long result = decrypt(number, KEY);
        return String.valueOf(result) + last;

    }


    // 加密算法可以公开
    private static long encrypt(long plainText, int key) {
        return plainText ^ key;
    }

    // 解密算法也可以公开
    private static long decrypt(long cipherText, int key) {
        return cipherText ^ key;
    }


}