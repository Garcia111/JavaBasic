package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 9:27 2020/1/8
 */
public class Parcel10 {

    public Destination destination(final String dest, final float price){
        return new Destination() {

            private int cost;

            {
                //此静态方法相当于匿名内部类的构造器
                cost = Math.round(price);
                if(cost >100){
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;


            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania",101.395F);
    }

}
