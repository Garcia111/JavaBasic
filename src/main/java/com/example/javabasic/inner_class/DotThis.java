package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 15:10 2020/1/7
 */
public class DotThis {
    
    void f(){
        System.out.println("DotThis.f()");
    }

    public class Inner{
        //从内部类获取外围类的引用
        public DotThis outer(){
            return DotThis.this;
        }
    }

    public Inner inner(){
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }



}
