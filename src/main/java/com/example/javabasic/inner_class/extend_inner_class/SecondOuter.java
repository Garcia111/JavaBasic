package com.example.javabasic.inner_class.extend_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 17:16 2020/1/8
 */
class FirstOuter{
    public class FirstInner{
        FirstInner(String s){
            System.out.println("FirstOuter.FirstInner() " + s);
        }
    }
}


public class SecondOuter {

    public class SecondInner extends FirstOuter.FirstInner{
        SecondInner(FirstOuter x){
            x.super("Hello");
            System.out.println("SecondOuter.SecondInner() ");
        }
    }

    public static void main(String[] args) {
        FirstOuter fo = new FirstOuter();
        SecondOuter so = new SecondOuter();
        SecondInner si = so.new SecondInner(fo);
    }
}
