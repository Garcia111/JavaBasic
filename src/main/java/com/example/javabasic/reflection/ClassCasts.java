package com.example.javabasic.reflection;

/**
 * @author：Cheng.
 * @date：Created in 22:54 2019/11/10
 */
class Building{}

class House extends Building{}


public class ClassCasts {

    public static void main(String[] args){
        Building b = new House();
        Class<House> houseType = House.class;
        House h= houseType.cast(b);
        //上下两种转型其实是达到了同样的效果
        h = (House)b;
    }

}
