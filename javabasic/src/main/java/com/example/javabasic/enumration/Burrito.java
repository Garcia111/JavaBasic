package com.example.javabasic.enumration;

import static com.example.javabasic.enumration.Spiciness.*;

/**
 * @author：Cheng.
 * @date：Created in 13:42 2019/8/26
 */
public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree){
        this.degree = degree;
    }

    @Override
    public String toString(){
        return "Burrito is"+degree;
    }

    //使用static import能够将enum实例的标识符带人当前的命名空间，无需再用enum类型来
    //修饰enum实例
    public static void main(String[] args){
        System.out.println(new Burrito(NOT));
        System.out.println(new Burrito(MEDIUM));
        System.out.println(new Burrito(HOT));
    }



































}

