package com.example.javabasic.inner_class.override_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 9:02 2020/1/9
 */
class Egg{
    private Yolk y;

    protected class Yolk{
        public Yolk(){
            System.out.println("Egg.Yolk()");
        }
    }

    public Egg(){
        System.out.println(" New Egg()" );
        y = new Yolk();
    }
}


public class BigEgg extends Egg{

    //覆盖Egg内部类
    public class Yolk{
        public Yolk(){
            System.out.println(" BigEgg.Yolk() ");
        }
    }

    public static void main(String[] args) {
        //调用BigEgg的默认构造器，调用BigEgg的默认构造器之前会调用Egg的构造器
        BigEgg egg = new BigEgg();
        egg.new Yolk();
    }
}






