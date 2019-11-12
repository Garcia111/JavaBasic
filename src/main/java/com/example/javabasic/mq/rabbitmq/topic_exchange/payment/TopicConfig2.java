package com.example.javabasic.mq.rabbitmq.topic_exchange.payment;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：Cheng.
 * @date：Created in 16:36 2019/11/12
 */
@Configuration
public class TopicConfig2 {

    @Bean
    public Queue paymentQueue(){
        return new Queue("api.payment");
    }


    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange("paymentExchange");
    }


    @Bean
    public Binding bindPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange){
        //bindingKey为 api.payment.#
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }
}
