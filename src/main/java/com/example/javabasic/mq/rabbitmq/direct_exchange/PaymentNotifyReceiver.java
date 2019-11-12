package com.example.javabasic.mq.rabbitmq.direct_exchange;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 11:17 2019/11/12
 */
@Slf4j
@Component
@RabbitListener(queues = "notify.payment")
public class PaymentNotifyReceiver {

    //监听routingKey为 notify.payment的队列消息

    @RabbitHandler
    public void receive(String msg){
        log.info("notify.payment receive message:"+msg);
    }

}
