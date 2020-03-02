/*
 * Copyright (C) 2020 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.schedule_task.quartz.customer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author wangchunming
 * @create 2019-08-22 18:16
 */
@Component
@ConfigurationProperties(prefix = "scheduler.start.time")
@Data
public class SchedulerConfig {
    private String alarmJob;
    private String timeZone;
}
