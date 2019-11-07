package com.example.javabasic;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author：Cheng.
 * @date：Created in 10:53 2019/11/7
 */
@ContextConfiguration
public class EventBusTest {

    @Autowired
    private EventBus  eventBus;

}
