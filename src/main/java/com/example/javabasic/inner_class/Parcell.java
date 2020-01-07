package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 11:10 2020/1/7
 */
public class Parcell {

    class Contents{
        private int i=11;
        public int value(){return i;}
    }

    class Destination{
        private String label;
        Destination(String whereTo){
            label = whereTo;
        }
        String readLabel(){return label;}
    }


    public void ship(String dest){
        //外部类可以直接访问内类
        Contents c = new Contents();
        Destination d = to(dest);
        System.out.println(d.readLabel());
    }


    public Destination to(String s){
        return new Destination(s);
    }

    public Contents contents(){
        return new Contents();
    }


    public static void main(String[] args) {
        Parcell p = new Parcell();
        p.ship("Tasmania");

        //如果想从外部类的非静态方法之外的任意位置创建某个内部类的对象，OuterClass.InnerClass
        Parcell q = new Parcell();
        Parcell.Contents c = q.contents();
        Parcell.Destination d = q.to("Borneo");
    }










}
