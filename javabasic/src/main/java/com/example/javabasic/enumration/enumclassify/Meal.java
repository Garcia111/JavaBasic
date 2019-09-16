package com.example.javabasic.enumration.enumclassify;

/**
 * @author：Cheng.
 * @date：Created in 10:45 2019/9/16
 */
public class Meal {

    public static void main(String[] args){
        for(int i = 0; i<5; i++){
            for(Course course : Course.values()){
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("----");
        }
    }
}
