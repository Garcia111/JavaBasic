package com.example.javabasic.enumration;

/**
 * @author：Cheng.
 * @date：Created in 10:55 2019/8/26
 */
public enum EnumExample {

    //所创建的类型都是Enum类的子类
    //枚举类型的每一个值都将映射到protected Enum(String name, int ordinal)构造函数中。
    // 每个值的名称都会被转换成一个字符串，并且序数ordinal设置表示是了此设置被创建的顺序。

    MON(1),TUE(2),WED(3),THU(4),FRI(5),SAT(6),SUN(0);

    //给enum对象加一下value的属性和getValue()的方法
    //如果你打算定义自己的方法，那么必须在enum实例序列的最后添加一个分号。同时Java要求你必须先定义enum实例。
    //如果在定义enum实例之前定义了任何方法或者属性，在编译时会得到错误信息。

    private int value;

    EnumExample(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
