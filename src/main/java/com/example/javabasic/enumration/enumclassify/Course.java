package com.example.javabasic.enumration.enumclassify;

import com.example.javabasic.enumration.Enums;

/**
 * @author：Cheng.
 * @date：Created in 10:37 2019/9/16
 */
public enum Course {

    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCource.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    WINE(Food.Wine.class);

    private Food[] values;

    private Course(Class<? extends  Food> kind){
        values = kind.getEnumConstants();
    }


    public Food randomSelection(){
        return Enums.random(values);
    }

    //在上面的程序中，每一个Course的实例都将其对应的Class对象作为构造器的参数
}
