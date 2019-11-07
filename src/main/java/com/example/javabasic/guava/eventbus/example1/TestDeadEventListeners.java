package com.example.javabasic.guava.eventbus.example1;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author：Cheng.
 * @date：Created in 14:36 2019/11/7
 */
public class TestDeadEventListeners {

    @Test
    public void testDeadEventListeners() throws Exception {

        EventBus eventBus = new EventBus("testDeadEvent");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));

        System.out.println("deadEvent:"+deadEventListener.isNotDelivered());

    }
}

