package com.example.javabasic.dto;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 21:26 2019/9/7
 */
@Accessors(chain = true)
@Getter
@Setter
public class PersonInfo {


    private String name;

    private int age;

    private Integer weight;

    private double height;
}
