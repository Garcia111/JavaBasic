package com.example.javabasic.enumration.enumclassify;

/**
 * @author：Cheng.
 * @date：Created in 10:13 2019/9/16
 */
public interface Food {
    //在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组，可以达到将枚举元素分类组织的目的。
    //eg：假设你想使用enum来表示不同类别的食物，同时还希望每个enum仍然保持Food类型。
    enum Appetizer implements Food{
        SALAD,SOUP,SPRING_ROLLS;
    }

    enum MainCource implements Food{
        LASAGNE, BURRITO, PAD_THAT,
        LENTILS, HUMMOUS, VINDALOO;
    }

    enum Dessert implements Food{
        TIRAMISU, GELATO, BLACK_FOREST_CAKE,
        FRUIT, CREME_CARMEL;
    }

    enum Coffee implements Food{
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE,CAPPUCCINO, TEA, HERB_TEA;
    }

    enum Wine implements Food{
        MAOTAI, XO;
    }
}
