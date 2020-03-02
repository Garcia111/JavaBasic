package com.example.javabasic.config;

import com.example.javabasic.schedule_task.quartz.customer.QuartzJob;
import com.example.javabasic.schedule_task.quartz.customer.SchedulerConfig;
import com.example.javabasic.schedule_task.quartz.example.SampleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.TimeZone;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail sampleJobDetail(){
        return JobBuilder.newJob(SampleJob.class).withIdentity("sampleJob")
                .usingJobData("name","World").storeDurably().build();
    }


    @Bean
    public Trigger sampleJobTrigger(){
        //使用SimpleScheduleBuilder 每隔2s执行一次
        SimpleScheduleBuilder scheduleBuilder =
                SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
                .repeatForever();

        //将Trigger JobDetail 和Scheduler进行绑定
        return TriggerBuilder.newTrigger().forJob(sampleJobDetail()).withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder).build();
    }


    @Bean
    public SchedulerFactoryBeanCustomizer schedulerFactoryBeanCustomizer(DataSource dataSource) {
        //quartz参数
        Properties prop = new Properties();
        //配置实例
        prop.put("org.quartz.scheduler.instanceId","AUTO");
        //线程池配置
        prop.put("org.quartz.threadPool.threadCount","5");
        //JobStore配置
        prop.put("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.tablePrefix","QRTZ_");


        //Spring提供SchedulerFactoryBean为Scheduler提供配置信息，并被Spring容器管理其生命周期
        return factory -> {
            factory.setQuartzProperties(prop);
            factory.setDataSource(dataSource);
            //启动时更新已经存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应的记录了
            factory.setOverwriteExistingJobs(true);
            //当spring关闭时，会等待所有已经启动的quartz job结束之后，Spring才能完全shutdown
            factory.setWaitForJobsToCompleteOnShutdown(true);
        };
    }



    @Autowired
    private SchedulerConfig config;

    private final String ALARM_ID = "alarmQuartz";
    private final String QUERY_CAPACITY_ID = "queryCapacityQuartz";


    @Bean
    public JobDetail alarmQuartzDetail() {
        JobDataMap map = new JobDataMap();
        map.put("jobName", ALARM_ID);

        return JobBuilder.newJob(QuartzJob.class)
                .setJobData(map)
                .withIdentity(ALARM_ID)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger alarmQuartzTrigger() {
        //使用CronScheduleBuilder 每天十一点跑定时任务
        CronScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule(config.getAlarmJob());
        scheduleBuilder.inTimeZone(TimeZone.getTimeZone(config.getTimeZone()));
        return TriggerBuilder.newTrigger()
                .forJob(alarmQuartzDetail())
                .withIdentity(ALARM_ID)
                .withSchedule(scheduleBuilder)
                .build();
    }

}









