package com.example.javabasic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 11:55 2019/9/8
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Department {

    private Integer id;

    private String name;

    private List<Department> childDeps;
}
