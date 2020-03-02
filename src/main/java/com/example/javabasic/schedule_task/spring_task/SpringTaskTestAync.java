package com.example.javabasic.schedule_task.spring_task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author：Cheng.
 * @date：Created in 17:00 2019/12/2
 */
@Slf4j
@Component
public class SpringTaskTestAync {

    //因为在定时任务方法上添加了@Async注解，因此下面的定时任务是可以在多个线程中并行执行

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
//        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
//        log.info("=====>>>>>scheduled---ThreadName:"+Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
//        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
//        log.info("=====>>>>>scheduled1---ThreadName:"+Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
//        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
//        log.info("=====>>>>>scheduled2---ThreadName:"+Thread.currentThread().getName());
    }

}
