/*
 * Copyright (C) 2019 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.mq.main;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** Created by Di on 2017/7/4. */
@Component
@ConfigurationProperties(prefix = "custom.amqp")
public class AmqpConfiguration {

    private int maxPoolSize;
    private int corePoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getKeepAliveSeconds() {
        return keepAliveSeconds;
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
