package com.example.javabasic.util;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameCheckUtil {

    public static Boolean nameCheck(String name) {
        String regexIsHanZi = "[\\u4e00-\\u9fa5]+";
        String re = "^[\u4e00-\u9fa5]+[·•][\u4e00-\u9fa5]+$";
        if (checkName(regexIsHanZi, name)) {//不包含点，验证长度为【2,8】
            if ((name.length() >= 2) & (name.length() <= 8)) {
                return true;
            }
        } else if (checkName(re, name)) {//包含点
            if ((name.length() <= 20)) {
                return true;
            }
        }
        return false;
    }

    /*验证两种模式的名字，包含点和不包含点*/
    public static Boolean checkName(String reg, String name) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
