package com.example.javabasic.inner_class.interface_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 11:36 2020/1/8
 */
public interface TestBed {

    public void f();

    public static void main(String[] args) {
        TestBed t = new TestBed() {
            @Override
            public void f() {

            }
        };
        t.f();
    }

}
