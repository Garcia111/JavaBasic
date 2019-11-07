package com.example.javabasic.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestLambda1 {

    public static void main(String[] args){
        List<String>  names= Arrays.asList("peter","ana","mike","xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(names);

        //lambda写法
        //Java编译器可以自动推导出参数类型，所以可以不用再写一次类型。
        Collections.sort(names,(a,b)-> b.compareTo(a));
    }
}
