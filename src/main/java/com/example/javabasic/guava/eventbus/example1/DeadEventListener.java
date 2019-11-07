package com.example.javabasic.guava.eventbus.example1;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author：Cheng.
 * @date：Created in 14:33 2019/11/7
 */
public class DeadEventListener {

    boolean notDelivered = false;

    @Subscribe
    public void listen(DeadEvent event){
        notDelivered = true;
    }


    public boolean isNotDelivered(){
        return notDelivered;
    }
}
