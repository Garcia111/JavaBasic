package com.example.javabasic.designpattern.singleton_pattern;

/**
 * @author：Cheng.
 * @date：Created in 11:20 2019/12/9
 */
public class ChocolateBoiler {

    private boolean empty;

    private boolean boiled;

    private static ChocolateBoiler chocolateBoiler;

    private ChocolateBoiler() {
        this.empty = true;
        this.boiled = false;
    }

    public static ChocolateBoiler getInstance(){
        if(chocolateBoiler==null){
            chocolateBoiler = new ChocolateBoiler();
        }
        return chocolateBoiler;
    }

}
