package com.example.javabasic.dto;

import com.example.javabasic.retention.TransformFiled;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * @author：Cheng.
 * @date：Created in 19:28 2019/9/7
 */
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @TransformFiled
    private String name;

    @TransformFiled
    private int age;

    private Integer weight;

    private double height;



    public static void main(String[] args){
        //在去写某个bean的时候，会有一些必须输入的字段，比如Person中的name字段，
        //一般处理方式是将name字段包装成一个构造方法，只有传入name这样的构造方法才能创建一个Student对象
        //使用lombok中的@RequiredArgsConstructor和@NonNull方法可以实现将某个filed设置成必传

        Person p = new Person().setName("zhangsan").setAge(11);
//        Person lisi = Person.builder().name("lisi").age(12).build();
    }
}
