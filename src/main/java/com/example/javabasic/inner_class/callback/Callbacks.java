package com.example.javabasic.inner_class.callback;

/**
 * @author：Cheng.
 * @date：Created in 15:11 2020/1/8
 */
interface Incrementable{
    void increment();
}

class Callee1 implements Incrementable{

    private int i = 0;

    @Override
    public void increment() {
        i++;
        System.out.println("i = " + i);
    }
}


class MyIncrement{
    public void increment(){
        System.out.println("Other operation" );
    }

    static void f(MyIncrement mi){
        mi.increment();
    }
}


class Callee2 extends MyIncrement{
    private int i=0;

    @Override
    public void increment(){
        super.increment();
        i++;
        System.out.println("i = " + i);
    }

    private class Clousure implements Incrementable{

        @Override
        public void increment() {
            //从内部类中访问外围类，这就是所说的回调吗？
            Callee2.this.increment();
        }
    }

    //通过外围类获得内部类对象
    Incrementable getCallbackReference(){
        return new Clousure();
    }
}

class Caller{

    private Incrementable callbackReference;

    Caller(Incrementable cbh){callbackReference = cbh;}

    void go(){callbackReference.increment();}
}


public class Callbacks {

    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        System.out.println("===========");
        caller1.go();
        caller1.go();
        System.out.println("===========");
        //首先调用内部类Clousure的increment()，然后回调外围类中的increment()，外围类中的increment又调用
        //外围类基类中的increment()，可以通过内部类来回调外围类中的方法
        caller2.go();
        caller2.go();
    }
}










