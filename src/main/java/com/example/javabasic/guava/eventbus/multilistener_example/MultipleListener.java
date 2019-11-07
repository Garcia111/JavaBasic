package com.example.javabasic.guava.eventbus.multilistener_example;

import com.google.common.eventbus.Subscribe;

/**
 * 一个订阅多个事件源的listener，只要在其多个方法上添加@Subscribe注解即可实现对多个消息的订阅
 * @author：Cheng.
 * @date：Created in 14:23 2019/11/7
 */
public class MultipleListener {
    public Integer lastInteger;
    public Long lastLong;

    @Subscribe
    public void listenInteger(Integer event) {
        lastInteger = event;
        System.out.println("event Integer:"+lastInteger);
    }

    @Subscribe
    public void listenLong(Long event) {
        lastLong = event;
        System.out.println("event Long:"+lastLong);
    }

    public Integer getLastInteger() {
        return lastInteger;
    }

    public Long getLastLong() {
        return lastLong;
    }
}
