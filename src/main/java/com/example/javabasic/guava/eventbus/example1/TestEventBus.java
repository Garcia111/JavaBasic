package com.example.javabasic.guava.eventbus.example1;

import com.example.javabasic.guava.eventbus.example1.EventListener;
import com.example.javabasic.guava.eventbus.example1.TestEvent;
import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author：Cheng.
 * @date：Created in 11:28 2019/11/7
 */
public class TestEventBus {
    @Test
    public void testReceiveEvent() throws Exception {
        //1.定义一个EventBus
        EventBus eventBus = new EventBus("test");
        //2.定义一个EventListener
        EventListener listener = new EventListener();
        //3.向EventBus注册EventListener
        eventBus.register(listener);
        //4.eventBus dispatcher event
        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

        System.out.println("LastMessage:"+listener.getLastMessage());
    }
}
