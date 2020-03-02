package com.example.javabasic.schedule_task.quartz.example2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.experimental.theories.DataPoints;

import java.io.Serializable;
import java.util.Date;

/**
 * @author：Cheng.
 * @date：Created in 10:51 2020/3/1
 */
@Data
@ToString
@EqualsAndHashCode
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long jobId;
    /**执行的类名*/
    private String beanName;
    /**执行的方法名*/
    private String methodName;
     /**参数*/
    private String params;
     /**cron表达式*/
    private String cronExpression;
     /**任务状态，0运行，1暂停*/
    private Byte status;
     /**备注*/
    private String remark;
     /**创建时间*/
    private Date createTime;

}
