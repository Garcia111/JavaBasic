package com.example.javabasic.guava.eventbus.asynceventbus;

import com.google.common.eventbus.AsyncEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author：Cheng.
 * @date：Created in 15:24 2019/11/7
 */
@Component
public class AsyncEventBusCenter {

    @Autowired
    SpringContextUtils mySpringContextUtils;

    private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));

    @PostConstruct
    public void before(){
        List<Object> listeners = mySpringContextUtils.getBeansWithAnnotation(AsyncEventListener.class);
        listeners.stream().forEach(listener->{asyncEventBus.register(listener);});
    }


    public void postAsync(Object event){
        asyncEventBus.post(event);
    }



}
