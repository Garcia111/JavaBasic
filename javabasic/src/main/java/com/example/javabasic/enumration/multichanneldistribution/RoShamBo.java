package com.example.javabasic.enumration.multichanneldistribution;

import com.example.javabasic.enumration.Enums;

/**
 * @author：Cheng.
 * @date：Created in 14:29 2019/9/17
 */
public class RoShamBo {


    public static <T extends Competitor<T>> void match(T a, T b){
        System.out.println(a + " vs. " + b +":" + a.compete(b));
    }


    public static <T extends  Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size){
        for(int i = 0; i<size; i++){
            match(Enums.random(rsbClass),Enums.random(rsbClass));
        }
    }
}
