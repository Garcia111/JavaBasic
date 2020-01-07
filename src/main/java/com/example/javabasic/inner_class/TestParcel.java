package com.example.javabasic.inner_class;

/**
 * @author：Cheng.
 * @date：Created in 16:08 2020/1/7
 */
class Parcel4{

    //内部类PContents是private，所以除了其外围类，其他类都不能够访问它
    private class PContents implements Contents{

        private int i = 11;

        @Override
        public int value(){return i;}
    }

    protected class PDestination implements Destination{

        private String label;

        private PDestination(String whereTo){
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s){
        return new PDestination(s);
    }

    //这里将PContents向上转型为Contents之后，因为其他类无法访问PContents，因此无法将返回值向下转型
    public Contents contents(){
        return new PContents();
    }

}

public class TestParcel {

    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Contents c = p.contents();

        Destination d = p.destination("Tasmania");
    }

}
