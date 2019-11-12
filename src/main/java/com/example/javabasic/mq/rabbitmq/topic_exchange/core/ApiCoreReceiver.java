package com.example.javabasic.mq.rabbitmq.topic_exchange.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 15:03 2019/11/12
 */
@Slf4j
@Component
//“api.core”为队列的名字
@RabbitListener(queues = "api.core")
public class ApiCoreReceiver {

    //监听routingKey为api.core的队列消息

    @RabbitHandler
    public void user(String msg){
        log.info("api.core receive message:"+msg);
    }
}
