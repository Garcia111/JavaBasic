package com.example.javabasic.inner_class.local_inner_class;

/**
 * @author：Cheng.
 * @date：Created in 10:42 2020/1/9
 */
interface Counter{
    int next();
}

public class LocalInnerClass {

    private int count = 0;

    Counter getCounter(final String name){
        //方法内部的局部内部类
        class LocalCounter implements Counter{

            public LocalCounter(){
                System.out.println("LocalCounter in method " );
            }

            @Override
            public int next() {
                System.out.println(name);
                return count++;
            }
        }
        return new LocalCounter();
    }

    Counter getCounter2(final String name){
        //匿名内部类
        return new Counter() {

            {
                System.out.println(" Anonymous Counter Inner Class " );
            }

            @Override
            public int next() {
                System.out.println(name);
                return count++;
            }
        };
    }

    public static void main(String[] args) {
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local Inner");
        Counter c2 = lic.getCounter2("Anonymous inner");

        for(int i=0; i<5; i++){
            System.out.println(c1.next());
        }
//        for(int i=0; i<5; i++){
//            System.out.println(c2.next());
//        }
    }

}










