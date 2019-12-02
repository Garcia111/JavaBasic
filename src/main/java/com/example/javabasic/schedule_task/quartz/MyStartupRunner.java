package com.example.javabasic.schedule_task.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

/**
 * 触发定时任务：在项目启动时执行
 */
public class MyStartupRunner implements CommandLineRunner {
    @Autowired
    public CronSchedulerJob scheduleJobs;

    @Override
    public void run(String... args) throws Exception {
        scheduleJobs.scheduleJobs();
        System.out.println(">>>>>>>>>>>>>>>定时任务开始执行<<<<<<<<<<<<<");
    }
}
