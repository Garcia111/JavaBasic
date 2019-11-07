package com.example.javabasic.guava.eventbus.example1;

/**
 * @author：Cheng.
 * @date：Created in 11:27 2019/11/7
 */
public class TestEvent {
    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }
    public int getMessage() {
        return message;
    }
}
