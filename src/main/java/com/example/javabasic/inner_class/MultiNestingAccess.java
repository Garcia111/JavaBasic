package com.example.javabasic.inner_class;

/** 从多层嵌套类中访问外部类的成员
 * @author：Cheng.
 * @date：Created in 13:45 2020/1/8
 */
class MNA{
    private void f(){}

    class A{
        private void g(){}

        public class B{
            void h(){
                g();
                //在内部类里面可以透明的访问所有的外围类的成员，即使该成员是用private修饰的
                f();
            }
        }
    }
}


public class MultiNestingAccess {

    public static void main(String[] args) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
}
