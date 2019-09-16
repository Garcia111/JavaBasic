package com.example.javabasic.enumration.enumclassify;


import com.example.javabasic.enumration.Enums;

/** 练习题4
 * @author：Cheng.
 * @date：Created in 11:47 2019/9/16
 */
public enum Meal2 {

    APPERLIZER(Food.Appetizer.class),
    MainCource(Food.MainCource.class),
    DESEERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class),
    WINE(Food.Wine.class);

    private Food[] values;

    Meal2(Class<? extends Food> food) {
        values = food.getEnumConstants();
    }

    public Food randomValue(){
        return Enums.random(values);
    }

    interface Food{
        enum Appetizer implements Food {
            SALAD,SOUP,SPRING_ROLLS;
        }

        enum MainCource implements Food {
            LASAGNE, BURRITO, PAD_THAT,
            LENTILS, HUMMOUS, VINDALOO;
        }

        enum Dessert implements Food {
            TIRAMISU, GELATO, BLACK_FOREST_CAKE,
            FRUIT, CREME_CARMEL;
        }

        enum Coffee implements Food {
            BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
            LATTE,CAPPUCCINO, TEA, HERB_TEA;
        }

        enum Wine implements Food {
            MAOTAI, XO;
        }
    }


    public static void main(String[] args){
        for(Meal2 m2: Meal2.values()){
            System.out.println(m2.randomValue());
        }
    }

}
