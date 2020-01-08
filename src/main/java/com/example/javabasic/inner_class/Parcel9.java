package com.example.javabasic.inner_class;

/**
 * 在匿名内部类中对参数进行初始化
 * @author：Cheng.
 * @date：Created in 18:01 2020/1/7
 */
public class Parcel9 {

    public Destination destination(final String dest){
        return new Destination() {

            private String label = dest;

            @Override
            public String readLabel() {
                return label;
            }
        };
    }


    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
    }
}
