package com.example.javabasic.reflection;

/**
 * @author：Cheng.
 * @date：Created in 18:35 2019/10/26
 */
public class GenericClassReferences {


    //普通的类引用不会产生警告信息，尽管泛型类引用只能赋值为指向其声明的类型；
    //但是普通的类引用可以被重新赋值为指向其他的Class对象。

    public static void main(String[] args){
        //普通的类引用可以被重新赋值为指向任何其他的Class独享
        Class intClass = int.class;
        intClass = double.class;

        //泛型类引用只能赋值为指向其声明的类型
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class;
//      编译无法通过 genericIntClass = Double.class;


        //下面无法编译通过，因为Integer Class对象不是Number Class对象的子类
//        Class<Number> genericNumberClass = int.class;



        //使用通配符？,表示任何事物。
        Class<?> generalClass = int.class;
        generalClass = double.class;
        generalClass = Double.class;


        //为了创建一个Class引用，它被限定为某种类型，或者该类型的任何子类型，
        // 你需要将通配符与extends关键字相结合，创建一个范围
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
    }
}
