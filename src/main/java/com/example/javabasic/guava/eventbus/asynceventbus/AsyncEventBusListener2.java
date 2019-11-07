package com.example.javabasic.guava.eventbus.asynceventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 15:26 2019/11/7
 */
@Component
@AsyncEventListener
public class AsyncEventBusListener2 {


    @Subscribe
    @AllowConcurrentEvents
    public void subscribe(Object object){
        System.out.println("listener2 收到："+object);
    }
}
