package com.example.javabasic.enumration.enumset;

import java.util.EnumMap;

/**
 * @author：Cheng.
 * @date：Created in 14:03 2019/9/16
 */
public class TestEnumMap {

    //EnumMap是一种特殊的Map，它要求其中的键Key必须来自于一个enum，由于enum本身的限制，所以EnumMap在内部可以由
    //数组实现，因此EnumMap的速度很快，可以放心地使用enum实例在EnumMap中进行查找操作。


    public static void main(String[] args){
        Day today = Day.FRIDAY;

        EnumMap enummap=new EnumMap(Day.class); //对应Day类的EnumMap
        enummap.put(Day.MONDAY, "work work");   //第一个值为key不能为null，第二个值为values可以为null
        enummap.put(Day.TUESDAY, "work work");  //第一个值填入枚举实例，第二个值为该枚举实例的记录信息
        enummap.put(Day.WEDNESDAY, "work work");
        enummap.put(Day.THURSDAY, "work work");
        enummap.put(Day.FRIDAY, "work work");
        enummap.put(Day.SATURDAY, "have fun");
        enummap.put(Day.SUNDAY, "have fun");

        System.out.println(enummap);

        String job=(String) enummap.get(today);
        System.out.println("job is:"+job);

    }
}
