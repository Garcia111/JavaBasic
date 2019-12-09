package com.example.javabasic.designpattern.singleton_pattern;

/**
 * @author：Cheng.
 * @date：Created in 11:41 2019/12/9
 */
public class SingletonHungryPattern {

    //只要JVM一加载这个类就会创建实例，而不是等到需要的时候才会创建
    private static SingletonHungryPattern singletonHungryPattern = new SingletonHungryPattern();

    private SingletonHungryPattern() {
    }


    public static SingletonHungryPattern getInstance(){
        return singletonHungryPattern;
    }
}
