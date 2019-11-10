package com.example.javabasic.java8.Stream;

import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

/**
 * @author：Cheng.
 * @date：Created in 16:37 2019/11/10
 */
public class ReduceTest {

    @Test
    public void reduce(){
        //规约？？
        List<String> list = Lists.newArrayList("Monica","Chandler","Rachel","Ross","Joey","Phebi");
        Optional<String> reduced = list.stream().sorted().reduce((s1, s2)->s1+"#"+s2);
        reduced.ifPresent(System.out::println);
    }
}
