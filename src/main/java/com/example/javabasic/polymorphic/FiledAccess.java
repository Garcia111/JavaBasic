package com.example.javabasic.polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 14:58 2020/1/9
 */
class Super{
    public int field = 0;

    public int getField(){
        return field;
    }
}


class Sub extends Super{
    public int field = 1;

    @Override
    public int getField() {
        return field;
    }

    public int getSuperField(){
        return super.field;
    }
}

public class FiledAccess {

    public static void main(String[] args) {
        Super sup = new Sub();
        //变量的访问不会多态，只有方法的访问会多态，这里输出的sup.filed真的是Super的变量，而不是子类的field
        System.out.println("sup.field = " + sup.field+", sup.getField() = "+sup.getField());


        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field+", sub.getField() = "+sub.getField()
                            +", sub.getSuperField() = "+sub.getSuperField());

    }

}











