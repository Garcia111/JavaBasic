package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 15:47 2020/1/7
 */
public class DotNew {

    public class Inner{}


    public static void main(String[] args) {
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
    }
}
