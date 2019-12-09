package com.example.javabasic.designpattern.singleton_pattern;

/**
 * @author：Cheng.
 * @date：Created in 11:43 2019/12/9
 */
public class SingletonLazyPattern {

    //volatile关键词确保可见性
    // ，当实例被初始化时，多个线程能够正确地处理
    private volatile static SingletonLazyPattern singletonLazyPattern;


    private SingletonLazyPattern(){
    }


    public static SingletonLazyPattern getInstance(){
        if(singletonLazyPattern == null){
            synchronized (SingletonLazyPattern.class){
                if(singletonLazyPattern == null){
                    singletonLazyPattern = new SingletonLazyPattern();
                }
            }
        }
        return singletonLazyPattern;
    }


}
