package com.example.javabasic.polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 15:29 2020/1/9
 */
class StaticSuper{

    public static String staticGet(){
        return "Base staticGet()";
    }

    public String dynamicGet(){
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper{
    public static String staticGet(){
        return "Derived staticGet()";
    }

    @Override
    public String dynamicGet(){
        return "Derived dynamicGet()";
    }
}


public class StaticPolymorphism {

    public static void main(String[] args) {
        StaticSuper sup = new StaticSub();
        System.out.println(sup.staticGet());
        System.out.println(sup.dynamicGet());
    }

}
