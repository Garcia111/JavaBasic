package com.example.javabasic;

import com.example.javabasic.guava.eventbus.asynceventbus.AsyncEventBusCenter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author：Cheng.
 * @date：Created in 15:30 2019/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAsyncEventListener {

    @Autowired
    AsyncEventBusCenter asyncEventBusCenter;

    @Test
    public void test() throws InterruptedException {
        System.out.println("开始发送消息");
        asyncEventBusCenter.postAsync("您有一条新的消息");
        System.out.println("开始睡眠");
        TimeUnit.SECONDS.sleep(5L);
    }
}
