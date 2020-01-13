package com.example.javabasic.polymorphic.constructor_and_polymorphic;

/**
 * @author：Cheng.
 * @date：Created in 13:53 2020/1/13
 */
class Grain{
    @Override
    public String toString() {
        return "Grain";
    }
}

class Wheat extends Grain{
    @Override
    public String toString() {
        return "Wheat";
    }
}

class Mill{
    Grain process(){
        return new Grain();
    }
}

class WheatMill extends Mill{
    @Override
    Wheat process(){
        return new Wheat();
    }
}

public class CovarianReturn {

    public static void main(String[] args) {
        Mill m = new Mill();
        Grain g = m.process();
        System.out.println(g);
        m= new WheatMill();
        g = m.process();
        System.out.println(g);
    }

}
