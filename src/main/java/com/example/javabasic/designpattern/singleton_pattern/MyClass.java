package com.example.javabasic.designpattern.singleton_pattern;

/**
 * @author：Cheng.
 * @date：Created in 9:28 2019/12/9
 */
public class MyClass {

    private static MyClass myClassObject;

    private MyClass(){}

    public static MyClass getInstance(){
        if(myClassObject == null){
            myClassObject =  new MyClass();
        }
        return myClassObject;
    }

}
