package com.example.javabasic.mq.rabbitmq.topic_exchange.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 15:13 2019/11/12
 */
@Slf4j
@Component
public class ApiCoreSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void user(String msg){
        log.info("api.core.user send message:"+msg);
        //routingKey: api.core.user
        rabbitTemplate.convertAndSend("coreExchange","api.core.user",msg);
    }


    public void userQuery(String msg){
        log.info("api.core.user.query send message:"+msg);
        //routingKey: api.core.user.query
        rabbitTemplate.convertAndSend("coreExchange","api.core.user.query",msg);
    }


}
