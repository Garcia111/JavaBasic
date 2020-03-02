package com.example.javabasic.schedule_task.quartz.customer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author：Cheng.
 * @date：Created in 10:16 2019/12/3
 */
public class QuartzJob extends AbstractQuartzJobBean {

    @Autowired
    QuartzTestService quartzTestService;

    @Override
    void doExecuteInternal(JobExecutionContext context) {
        quartzTestService.quartzJob();
    }
}
