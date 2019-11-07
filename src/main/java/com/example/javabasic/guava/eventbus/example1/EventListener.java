package com.example.javabasic.guava.eventbus.example1;

import com.example.javabasic.guava.eventbus.example1.TestEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author：Cheng.
 * @date：Created in 11:28 2019/11/7
 */
public class EventListener {
    public int lastMessage = 0;


    /**使用Guava之后，如果要订阅消息，就不再集成指定的接口，只需要在指定的方法上加上@Subscribe即可，但是传入的参数只能为Event*/
    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
