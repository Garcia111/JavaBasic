package com.example.javabasic.inner_class;

/**
 * 匿名内部类的使用
 * @author：Cheng.
 * @date：Created in 17:24 2020/1/7
 */
public class Parcel7 {

    public Contents contents(){

        //实现了某个接口的匿名内部类
        return new Contents() {

            private int i=11;

            @Override
            public int value() {
                return i;
            }
        };
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }

}
