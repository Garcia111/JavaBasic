package com.example.javabasic.reflection;

/**
 * @author：Cheng.
 * @date：Created in 17:06 2019/10/22
 */
class Candy{
    static {System.out.println("Loading Candy");}
}

class Gum{
    static {System.out.println("Loading Gum");}
}

class Cookie{
    static{System.out.println("Loading Cookie");}
}

public class SweetShop {

    //无论何时，只要你想在运行时使用类型信息，就必须首先获得恰当的Class对象的引用。
    //使用Class.forName()适用于你没有该类的对象，而只是知道该类的完全限定名的情况；
    //如果已经用来了一个类的实例对象，可以通过调用getClass()方法来获取Class引用。
    public static void main(String[] args){
        System.out.println("inside main");
        new Candy();
        System.out.println("Aafter creating Candy");

        try{
            Class.forName("com.example.javabasic.reflection.Gum");
        }catch (ClassNotFoundException e){
            System.out.println("Could not find Gum");
        }
        System.out.println("After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
    }

}
