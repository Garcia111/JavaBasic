/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.mq.main;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender implements InitializingBean {

    public static final String EXCHANGE = "cop_create_table";
    public static final String ROUTING_KEY = "cop.create.table";

    @Autowired private RabbitTemplate rabbitTemplate;
    private String host;
    private AtomicInteger seq = new AtomicInteger();

    public void sendMsg(String pp) {
        MessageProperties mp = new MessageProperties();
        mp.setMessageId(host + seq.incrementAndGet());
        Message message = new Message(pp.getBytes(), mp);
        log.info("发送mq，创建企业基础表开始", "sendMsg", Sender.class);
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            InetAddress a = InetAddress.getLocalHost();
            host = a.getHostName() + ":createTable:";
        } catch (UnknownHostException e) {
            host = "unknow:createTable:";
        }
    }


    @Test
    public void getHost() throws UnknownHostException {
        InetAddress a = InetAddress.getLocalHost();
        String host = a.getHostName();

        System.out.println("HostName:"+host);
        System.out.println("HostAdrress:"+a.getHostAddress());
    }
}
