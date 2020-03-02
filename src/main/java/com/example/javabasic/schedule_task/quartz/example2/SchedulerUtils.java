package com.example.javabasic.schedule_task.quartz.example2;


import com.alibaba.fastjson.JSON;
import com.example.javabasic.schedule_task.quartz.customer.QuartzJob;
import com.example.javabasic.schedule_task.quartz.example.ScheduledJob;
import com.example.javabasic.schedule_task.quartz.example2.entity.ScheduleJob;
import org.quartz.*;

/**
 * @author：Cheng.
 * @date：Created in 10:16 2020/3/1
 */
public class SchedulerUtils {

    /**
     * 创建任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void createJob(Scheduler scheduler, ScheduleJob scheduleJob){
        Long jobId = scheduleJob.getJobId();;

        JobDetail job = JobBuilder.newJob(QuartzJob.class).withIdentity("JOB_"+jobId)
                                                            .build();
        //获取cron表达式，并创建对象
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                                                                    .withMisfireHandlingInstructionDoNothing();

        CronTrigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity("TRIGGER_"+jobId)
                                .withSchedule(cronScheduleBuilder)
                                .build();
        //将对象json序列化存储到Job的getJobDataMap()
        job.getJobDataMap().put("JOB_PARAM_KEY", JSON.toJSONString(scheduleJob));

        try {
            scheduler.scheduleJob(job,trigger);
            scheduler.pauseJob(JobKey.jobKey("JOB_"+jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("创建任务失败",e);
        }
    }


    /**
     * 更新定时任务
     * @param scheduler
     * @param scheduleJob
     */
    public static void updateJob(Scheduler scheduler, ScheduleJob scheduleJob){
        //获取新的Cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                                                                .withMisfireHandlingInstructionDoNothing();

        Long jobId = scheduleJob.getJobId();

        try{
            TriggerKey triggerKey = TriggerKey.triggerKey("TRIGGER_"+jobId);
            //获取原有的trigger
            CronTrigger trigger = (CronTrigger)scheduler.getTrigger(triggerKey);

            //为原有的trigger赋予新的cron表达式
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(cronScheduleBuilder).build();

            //执行原有的trigger更新
            scheduler.rescheduleJob(triggerKey,trigger);

        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("更新定时任务失败",e);
        }
    }

    /**
     * 删除任务
     * @param scheduler
     * @param jobId
     */
    public static void deleteJob(Scheduler scheduler,Long jobId){
        try{
            scheduler.deleteJob(JobKey.jobKey("JOB_"+jobId));
        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("删除定时任务失败",e);
        }
    }

    /**
     * 恢复任务
     * @param scheduler
     * @param jobId
     */
    public static void resumeJob(Scheduler scheduler, Long jobId){
        try{
            scheduler.resumeJob(JobKey.jobKey("JOB_"+jobId));
        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("恢复定时任务失败",e);
        }
    }


    /**
     * 立即执行任务
     * @param scheduler
     * @param jobId
     */
    public static void run(Scheduler scheduler,Long jobId){
        try{
            scheduler.triggerJob(JobKey.jobKey("JOB_"+jobId));
        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("立即执行定时任务失败",e);
        }
    }

    /**
     * 暂停任务
     * @param scheduler
     * @param jobId
     */
    public static void pauseJob(Scheduler scheduler,Long jobId){
        try{
            scheduler.pauseJob(JobKey.jobKey("JOB_"+jobId));
        }catch (SchedulerException e){
            e.printStackTrace();
            throw new RuntimeException("暂停定时任务失败",e);
        }
    }
}
