package com.example.javabasic.guava.eventbus.multilistener_example;

import com.google.common.eventbus.EventBus;

/**
 * @author：Cheng.
 * @date：Created in 14:24 2019/11/7
 */
public class TestMultiListenerEvents {

    public static void main(String[] args){
        //1.定义一个EventBus
        EventBus eventBus = new EventBus("testMultiListener");
        //2.定义一个EventListenerr
        MultipleListener multipleListener = new MultipleListener();
        //3.
        eventBus.register(multipleListener);
        //4.
        eventBus.post(new Long(10000000));
        eventBus.post(new Integer(100));
    }
}
