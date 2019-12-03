package com.example.javabasic.config;

import com.example.javabasic.schedule_task.quartz.customer.QuartzJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author：Cheng.
 * @date：Created in 10:21 2019/12/3
 */
@Configuration
public class CustomizeQuartzConfig {


    @Bean
    public JobDetail quartzJobDetail(){
        return JobBuilder.newJob(QuartzJob.class)
                .withIdentity("myQuartzJob")
                .usingJobData("name","myQuartzJob").storeDurably().build();
    }

    @Bean
    public Trigger quartzJobTrigger() {
        // 6的倍数秒执行 也就是 6 12 18 24 30 36 42 ....
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .forJob(quartzJobDetail())
                .usingJobData("name", "cronSchedulerJob1").withSchedule(scheduleBuilder).build();

        //将Trigger JobDetail 和Scheduler进行绑定
        return cronTrigger;
    }


}
