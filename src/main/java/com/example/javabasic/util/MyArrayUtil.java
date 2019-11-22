package com.example.javabasic.util;

import org.junit.Test;
import java.util.*;


/**
 * @author：Cheng.
 * @date：Created in 9:03 2019/11/22
 */
public class MyArrayUtil {

    @Test
    public void arrayToListWrong(){
        String[] arr = new String[]{"a","r","r","a","y"};
        List<String> list = Arrays.asList(arr);
        list.add("s");
    }


    /**
     * 数组转为list的正常方式
     */
    @Test
    public void arrayToListCorrect(){
        String[] arr = new String[]{"a","r","r","a","y"};
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arr));
        arrayList.add("s");
    }


}
