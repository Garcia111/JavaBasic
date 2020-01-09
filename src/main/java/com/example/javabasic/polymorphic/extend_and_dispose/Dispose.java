package com.example.javabasic.polymorphic.extend_and_dispose;

/**
 * @author：Cheng.
 * @date：Created in 17:27 2020/1/9
 */
class Characteristic{
    private String s;

    Characteristic(String s){
        this.s = s;
        System.out.println("Creating Characteristic = " + s);
    }

    protected void dispose(){
        System.out.println("disposing Characteristic  " + s);
    }

}
public class Dispose {
}
