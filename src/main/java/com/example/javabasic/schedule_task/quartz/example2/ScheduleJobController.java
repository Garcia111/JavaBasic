package com.example.javabasic.schedule_task.quartz.example2;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：Cheng.
 * @date：Created in 15:32 2020/3/2
 */
@RestController
@RequestMapping("/save")
@RequiresPermissions("schedule:job")
public class ScheduleJobController {
}
