package com.example.javabasic.enumration;

/**
 * @author：Cheng.
 * @date：Created in 11:06 2019/8/26
 */
public class EnumrationTest {

    public static void main(String[] args){
        for(EnumExample e:EnumExample.values()){
            System.out.println(e.toString());
            System.out.println("value:"+e.getValue());
        }

        //比较元素的序数，元素的序数表示了此元素被创建的顺序
        System.out.println(EnumExample.MON.compareTo(EnumExample.FRI));
        System.out.println("declareClass:"+EnumExample.MON.getDeclaringClass());
        System.out.println("name:"+EnumExample.MON.name());
        System.out.println("ordinal:"+EnumExample.MON.ordinal());
        System.out.println("toString:"+EnumExample.MON.toString());


    }



}
