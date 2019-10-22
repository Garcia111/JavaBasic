package com.example.javabasic.enumration;

import com.example.javabasic.enumration.enumclassify.Food;

/**
 * @author：Cheng.
 * @date：Created in 10:22 2019/9/16
 */
public class TypeOfFood {
    //如果enum类型实现了Food接口，那么我们就可以将其实例向上转型为Food。

    //当你需要与一大堆类型打交道时，接口就不如enum好用了，
    public static void main(String[] args){
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCource.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;
    }
}
