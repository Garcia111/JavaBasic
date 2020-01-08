package com.example.javabasic.inner_class.interface_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 11:23 2020/1/8
 */
public interface ClassInterface {

    void howdy();

    class Test implements ClassInterface{

        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }

    }
}
