package com.example.javabasic.mq.rabbitmq.topic_exchange.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 15:22 2019/11/12
 */
@Slf4j
@Component
public class ApiPaymentSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    public void order(String msg){
        log.info("api.payment.order send a message:"+msg);
        //第一个参数为要sender要发送消息的交换器的名字，第二个参数为routingKey,第三个参数为msg
        rabbitTemplate.convertAndSend("paymentExchange","api.payment.order",msg);
    }


    public void orderQuery(String msg){
        log.info("api.payment.order.query send a message:"+msg);
        rabbitTemplate.convertAndSend("paymentExchange","api.payment.order.query",msg);
    }


    public void orderDetailQuery(String msg){
        log.info("api.payment.order.detail.query send a message:"+msg);
        rabbitTemplate.convertAndSend("paymentExchange","api.payment.order.detail.query",msg);
    }

}
