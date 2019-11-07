package com.example.javabasic.guava.eventbus.example3;

import com.google.common.eventbus.Subscribe;

/**
 * @author：Cheng.
 * @date：Created in 14:44 2019/11/7
 */
public class IntegerListener {

    private Integer lastMessage;

    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("Message:"+lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}
