package com.example.javabasic.mq.rabbitmq.topic_exchange.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 15:06 2019/11/12
 */
@Slf4j
@Component
@RabbitListener(queues = "api.payment")
public class ApiPaymentReceiver {

    @RabbitHandler
    public void order(String msg){
        log.info("api.payment receive new message:"+msg);
    }
}
