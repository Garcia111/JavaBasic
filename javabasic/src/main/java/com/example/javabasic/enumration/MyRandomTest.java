package com.example.javabasic.enumration;

/**
 * @author：Cheng.
 * @date：Created in 16:50 2019/8/26
 */
public class MyRandomTest {

    public static void main(String[] args){
        for(int i=0;i<20;i++){
            System.out.println(Enums.random(MyActivity.class)+" ");
        }
    }
}

enum MyActivity{

    SITTING, LYING, STANDING, HOPPING, RUNNING, DODGING, JUMPING, FAILING, FLYING
}