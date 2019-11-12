package com.example.javabasic.mq.rabbitmq.topic_exchange.core;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：Cheng.
 * @date：Created in 14:36 2019/11/12
 */
@Configuration
public class TopicConfig {


    /**
     * 配置一个名字（or Binding key）为api.core的消息队列并绑定到coreExchange交换机上
     * @return
     */
    @Bean
    public Queue coreQueue(){
        return new Queue("api.core");
    }

    /**
     * 创建一个名为coreExchange的TopicExchange
     * @return
     */
    @Bean
    public TopicExchange coreExchange(){
        return new TopicExchange("coreExchange");
    }

    /**
     * 将coreQueue与coreExchange 通过指定的routing key进行绑定,"api.core.*"为BindingKey
     * @param coreQueue
     * @param coreExchange
     * @return
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange){
        //*-----用于匹配一个单词，#-----用于匹配0个或多个单词
        //bindingKey为 api.core.*
        //todo 为什么routingKey与bindingKey不匹配，receiver仍然可以接收消息呢
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("aapi.core");
    }




}
