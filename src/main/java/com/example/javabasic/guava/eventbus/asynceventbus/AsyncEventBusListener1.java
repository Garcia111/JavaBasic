package com.example.javabasic.guava.eventbus.asynceventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;


/**
 * @author：Cheng.
 * @date：Created in 15:14 2019/11/7
 */
@Component
@AsyncEventListener
public class AsyncEventBusListener1 {


    @Subscribe
    @AllowConcurrentEvents
    public void subscribe(Object object){
        System.out.println("listener1 收到："+object);
    }


}
