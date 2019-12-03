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

    //下面这三个定时任务是在同一个线程中串行执行的，如果有多个定时任务，如果这些任务中只有一个任务卡死，会导致其他任务无法执行。

    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
        log.info("=====>>>>>scheduled---ThreadName:"+Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
        log.info("=====>>>>>scheduled1---ThreadName:"+Thread.currentThread().getName());
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
        log.info("=====>>>>>scheduled2---ThreadName:"+Thread.currentThread().getName());
    }

}
