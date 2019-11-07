package com.example.javabasic.designpattern.ObserverPattern.selfobserverpattern;

/**
 * 主题：观察者模式中一的一方
 * @author：Cheng.
 * @date：Created in 14:21 2019/11/6
 */
public interface Subject {

    public void registerObserver(Observer o);


    public void removeObserver(Observer o);

    /**
     * 当主题状态改变时，这个方法会被调用，以通知所有的观察者
     */
    public void notifyObservers();
}
