package com.example.javabasic.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

/**
 * @author：Cheng.
 * @date：Created in 18:37 2019/9/4
 */
@Setter
@Getter
@Data
@ToString
public class Apple {

    private int id;

    private int weight;

    private String color;

    private Date Birthday;


    public Apple(int id, int weight, String color, Date birthday){
        this.id = id;
        this.weight = weight;
        this.color = color;
        Birthday = birthday;
    }


}
