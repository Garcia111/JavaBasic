package com.example.javabasic.config;

import com.example.javabasic.schedule_task.quartz.example.SampleJob;
import org.quartz.*;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

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


    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(DataSource dataSource) {
        //Spring提供SchedulerFactoryBean为Scheduler提供配置信息，并被Spring容器管理其生命周期
        return factory -> {
            factory.setDataSource(dataSource);
            //启动时更新已经存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应的记录了
            factory.setOverwriteExistingJobs(true);
            //当spring关闭时，会等待所有已经启动的quartz job结束之后，Spring才能完全shutdown
            factory.setWaitForJobsToCompleteOnShutdown(true);
        };
    }
}









