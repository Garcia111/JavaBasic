package com.example.javabasic.inner_class.static_inner_class;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.example.javabasic.inner_class.Contents;
import com.example.javabasic.inner_class.Destination;

/**
 * @author：Cheng.
 * @date：Created in 10:55 2020/1/8
 */
public class Parcel11 {

    private static class ParcelContents implements Contents{

        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class ParcelDestination implements Destination{

        private String label;

        private ParcelDestination(String whereTo){
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }

        public static void f(){}

        //嵌套类内部可以包含static数据
        static int x =10;

        //定义在静态内部类 内部 的静态内部类
        static class AnotherLevel{
            public static void f(){}
            static int x = 10;
        }

    }


    public static Destination destination(String s){
        return new ParcelDestination(s);
    }


    public static Contents contents(){
        return new ParcelContents();
    }

    public static void main(String[] args) {
        Contents c = contents();
        Destination d = destination("Tasmania");
    }


}
