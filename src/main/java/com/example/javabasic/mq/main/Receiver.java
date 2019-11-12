/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.mq.main;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;


@Slf4j
@Component
public class Receiver implements InitializingBean {

    static Logger logger = LoggerFactory.getLogger(Receiver.class);

    public static final String EXCHANGE = "cop_create_table";
    public static final String ROUTING_KEY = "cop.create.table";
    public static final String QUEUE = "cop.create.table.queue";

    @Autowired RabbitTemplate rabbitTemplate;

    @Autowired AmqpConfiguration ac;

//    @Autowired IspService bs;

    @Override
    public void afterPropertiesSet() {

        AmqpAdmin admin = new RabbitAdmin(rabbitTemplate.getConnectionFactory());
        TopicExchange ex = new TopicExchange(EXCHANGE, true, true);
        admin.declareExchange(ex);

        Queue queue = new Queue(QUEUE, true);
        admin.declareQueue(queue);
        admin.declareBinding(BindingBuilder.bind(queue).to(ex).with(ROUTING_KEY));

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitTemplate.getConnectionFactory());
        container.setQueueNames(QUEUE);
        container.setDefaultRequeueRejected(false);

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(ac.getMaxPoolSize());
        taskExecutor.setCorePoolSize(ac.getCorePoolSize());
        taskExecutor.setKeepAliveSeconds(ac.getKeepAliveSeconds());
        taskExecutor.setQueueCapacity(ac.getQueueCapacity());
        taskExecutor.initialize();
        container.setTaskExecutor(taskExecutor);

        container.setErrorHandler(
                new ErrorHandler() {
                    @Override
                    public void handleError(Throwable throwable) {
                        logger.error(throwable.getMessage());
                    }
                });
        container.setMessageListener(
                new MessageListener() {

                    @Override
                    public void onMessage(Message msg) {
                        try {
                           log.info(
                                    "mq接收消息，开始开通企业创建基础表", "onMessage", Receiver.class);
//                            bs.createTable(new String(msg.getBody()));
                        } catch (Exception e) {
                            logger.error("发送数据操作记录失败", e.getMessage());
                        }
                    }
                });
        container.start();
    }
}
