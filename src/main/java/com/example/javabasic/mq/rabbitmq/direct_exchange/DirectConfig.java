package com.example.javabasic.mq.rabbitmq.direct_exchange;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：Cheng.
 * @date：Created in 11:14 2019/11/12
 */
@Configuration
public class DirectConfig {

    //配置一个routingKey为 notify.payment的消息队列

    @Bean
    public Queue  paymentNotifyQueue(){
        return new Queue("notify.payment");
    }
}
