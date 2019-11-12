package com.example.javabasic.mq.rabbitmq.direct_exchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 11:20 2019/11/12
 */
@Slf4j
@Component
public class PaymentNotifySender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 将消息发送至默认的交换机并且routingKey为 notify.payment
     * @param msg
     */
    public void sender(String msg){
        log.info("notify.payment send message:"+msg);
        rabbitTemplate.convertAndSend("notify.payment",msg);
    }






}
