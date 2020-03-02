/*
 * Copyright (C) 2018 China Telecom System Integration Co., Ltd.
 * All rights reserved.
 */
package com.example.javabasic.schedule_task.quartz.customer;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created on 2018/10/25.
 *
 * @author cuiwei
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractQuartzJobBean extends QuartzJobBean {

    @Override
    protected void executeInternal(@NonNull JobExecutionContext context)
            throws JobExecutionException {
        String name = context.getJobDetail().getKey().getName();
        log.info("[定时任务] 开始执行 ...");

        try {
            this.doExecuteInternal(context);

            log.info("[定时任务] 成功");
        } catch (Exception e) {
            log.error("[定时任务] 定时任务处理过程发生异常，执行失败 ...", e);
            throw new JobExecutionException(e);
        }
    }

    /**
     * Do execute internal.
     *
     * @param context the context
     */
    abstract void doExecuteInternal(JobExecutionContext context);
}
