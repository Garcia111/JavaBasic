package com.example.javabasic.enumration;

import static sun.misc.Version.print;

/**
 * @author：Cheng.
 * @date：Created in 9:16 2019/9/16
 */
enum Shrubbery{ GROUND, CRAWLING, HANGING}

public class EnumClass {

    public static void main(String[] args){
        for(Shrubbery s:Shrubbery.values()){
            System.out.println(s+" ordinal: "+s.ordinal());
            System.out.println(s.compareTo(Shrubbery.CRAWLING)+" ");
            System.out.println(s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);
            System.out.println(s.getDeclaringClass());
            System.out.println(s.name());
            System.out.println("============");
        }
    }

}
