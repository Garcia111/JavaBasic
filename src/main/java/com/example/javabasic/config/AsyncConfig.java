package com.example.javabasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author：Cheng.
 * @date：Created in 17:21 2019/12/2
 */
@Configuration
//@EnableAsync：开启异步事件的支持
@EnableAsync
public class AsyncConfig {

    //允许Spring 定时任务多线程执行
    @Bean
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(2000);
        executor.setQueueCapacity(10);
        executor.initialize();
        return executor;
    }


}
