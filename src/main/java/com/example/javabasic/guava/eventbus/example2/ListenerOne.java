package com.example.javabasic.guava.eventbus.example2;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Service;

/**
 * @author：Cheng.
 * @date：Created in 10:41 2019/11/7
 */
@Service
@EventBusListener
public class ListenerOne {

    @Subscribe
    public void doSomethingOne(EventOne eventOne){
        System.out.println("Listening do"+eventOne);
    }
}
