package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 15:55 2020/1/7
 */
public class Parcel3 {


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


//    public void ship(String dest){
//        //外部类可以直接访问内类
//        Parcell.Contents c = new Parcell.Contents();
//        Parcell.Destination d = to(dest);
//        System.out.println(d.readLabel());
//    }
//
//
//    public Parcell.Destination to(String s){
//        return new Parcell.Destination(s);
//    }
//
//    public Parcell.Contents contents(){
//        return new Parcell.Contents();
//    }


    public static void main(String[] args) {
        Parcel3 p = new Parcel3();

        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
    }
}
