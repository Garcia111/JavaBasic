package com.example.javabasic.reflection;

import java.util.Random;

/**
 * @author：Cheng.
 * @date：Created in 22:08 2019/10/25
 */
class Initable{
    //常数，是一个编译期常量，访问此变量不会初始化
    static final int staticFinal = 47;
    //对Initable.staticFinal2的访问将强制进行类的初始化，因为这不是一个编译期常量
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}


class Initable2{
    //非常数静态域
    static int staticNonFinal = 147;
    static {
        System.out.println("Initializing Initable2");
    }
}


class Initable3{
    //非常数静态域
    static int staticNonFinal = 74;
    static{
        System.out.println("Initializing Initable3");
    }
}


public class ClassInitialization {

    public static Random rand = new Random(47);


    //仅仅使用.class语法来获得对类的引用不会引发初始化。
    // 但是，为了产生Class应用，Class.forName()立即就进行了初始化.
    //如果一个static final值是“编译期常量”，就像Initable.staticFinal那样，那么这个值不需要对Initable类进行初始化就可以被读取
    //如果仅仅是将一个域设置为static和final的，还不能确保不初始化类就可以被读取。
    // 例如，对Initable.staticFinal2d的访问将强制进行类的初始化，因为它不是一个编译期常量。

    //如果一个static域不是final的，那么在对它访问时，总是要求在它被读取之前，要先进行链接（为这个域分配存储空间）
    //和初始化（初始化该存储空间）
    public static void main(String[] args) throws Exception{
        //仅仅使用.class来获得对类的引用不会引发初始化
        Class initable = com.example.javabasic.reflection.Initable.class;
        System.out.println("After creating Initable ref");
        System.out.println(Initable.staticFinal);//47

        //此处才会Initializing Initable
        System.out.println(Initable.staticFinal2);//258


        System.out.println(Initable2.staticNonFinal);//147
        //Class.forName()立即会进行初始化
        Class initable3 = Class.forName("com.example.javabasic.reflection.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
