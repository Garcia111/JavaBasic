package com.example.javabasic.schedule_task.quartz.customer;

import org.springframework.stereotype.Service;

/**
 * @author：Cheng.
 * @date：Created in 10:14 2019/12/3
 */
@Service
public class QuartzTestService {


    public void quartzJob(){
        System.out.println("this is a quartz job form service");
    }

}
