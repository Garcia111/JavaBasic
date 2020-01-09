package com.example.javabasic.inner_class.override_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 9:23 2020/1/9
 */
class Egg2{

    protected class Yolk{
        public Yolk(){}{
            //3.为什么又再次调用了这个方法？
            System.out.println(" Egg2.Yolk() ");
        }

        public void f(){
            System.out.println(" Egg2.Yolk.f() ");
        }
    }
    //1.首先初始化Yolk对象，调用内部类的构造方法
    private Yolk y = new Yolk();
    //2.其次调用外围类Egg2的构造方法
    public Egg2(){
        System.out.println(" New Egg2() ");
    }

    public void insertYolk(Yolk yy){
        y = yy;
    }

    public void g(){
        y.f();
    }
}

public class BigEgg2 extends Egg2{

    public class Yolk extends Egg2.Yolk{
        public Yolk(){
            System.out.println(" BigEgg2.Yolk() " );
        }

        @Override
        public void f(){
            System.out.println(" BigEgg2.Yolk.f() ");
        }
    }

    public BigEgg2(){
        //这里调用new Yolk()是新的派生类Yolk的构造方法，在加载派生Yolk类之前，要先加载原有的Yolk类
        Yolk y = new Yolk();
        //将派生类向上转型为原有的Yolk类，但是调用的f()是新的Yolk类的f()
        insertYolk(y);
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        System.out.println(" =========== ");
        e2.g();
    }

}
