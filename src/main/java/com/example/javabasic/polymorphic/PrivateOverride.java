package com.example.javabasic.polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 14:42 2020/1/9
 */
public class PrivateOverride {

    private void f(){
        System.out.println(" private f() " );
    }

    public static void main(String[] args) {
        PrivateOverride po = new Derived();
        po.f();
    }
}


class Derived extends PrivateOverride{
    public void f(){
        System.out.println(" public f() ");
    }
}








