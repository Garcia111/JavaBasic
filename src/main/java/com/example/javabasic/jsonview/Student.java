package com.example.javabasic.jsonview;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 17:35 2019/9/11
 */
@Getter
@Setter
@Accessors(chain = true)
public class Student {

    private Integer id;

    private Integer age;

    private String name;
}
