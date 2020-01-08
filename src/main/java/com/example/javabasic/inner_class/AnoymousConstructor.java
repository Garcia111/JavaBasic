package com.example.javabasic.inner_class;

/** 为匿名内部类创建一个构造器
 * @author：Cheng.
 * @date：Created in 9:02 2020/1/8
 */
abstract class Base{
    public Base(int i){
        System.out.println("Base constructor.i = " + i);
    }
    public abstract void f();
}

public class AnoymousConstructor {

    //在此例中，不要求变量i一定是final的，因为i被传递给匿名类的基类的构造器，并不会在匿名类内部被直接使用
    public static Base getBase(int i){
        return new Base(i) {

            { System.out.println("Inside instance initializer"); }

            @Override
            public void f() {
                System.out.println("In anoymous f()");
            }
        };
    }


    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }

}
