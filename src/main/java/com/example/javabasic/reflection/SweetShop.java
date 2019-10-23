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
