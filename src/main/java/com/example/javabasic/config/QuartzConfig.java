package com.example.javabasic.config;

import com.example.javabasic.schedule_task.quartz.SampleJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleJobDetail(){
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name","World").storeDurably().build();
    }


    @Bean
    public Trigger sampleJobTrigger(){
        //每隔2s执行一次
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                .repeatForever();

        //将Trigger JobDetail 和Scheduler进行绑定
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder).build();
    }
}









