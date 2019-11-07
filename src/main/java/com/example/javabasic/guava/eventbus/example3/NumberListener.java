package com.example.javabasic.guava.eventbus.example3;

import com.google.common.eventbus.Subscribe;

/**
 * @author：Cheng.
 * @date：Created in 14:43 2019/11/7
 */
public class NumberListener {
    //NumberListener是监听Number事件的消息，但是Number类型的子类型：Long Integer ...类型的事件也会同时监听
    private Number lastMessage;

    @Subscribe
    public void listen(Number integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}
