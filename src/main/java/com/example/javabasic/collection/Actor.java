package com.example.javabasic.collection;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 16:16 2019/10/27
 */
@Accessors(chain = true)
@ToString
@Data
public class Actor {

    private String name;

    private String sex;
}
