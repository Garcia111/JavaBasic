package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 16:49 2020/1/7
 */
public class Parcel5 {

    public Destination destination(String s){

        //一个定义在方法中的内部类，只能在方法内部访问
        class PDestination implements Destination{

            private String label;

            private PDestination(String whereTo){
                label = whereTo;
            }

            @Override
            public String readLabel() {
                return label;
            }

            @Override
            public String toString() {
                return "PDestination{" +
                        "label='" + label + '\'' +
                        '}';
            }
        }
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
        System.out.println("d = " + d);
    }

}
