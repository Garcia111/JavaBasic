package com.example.javabasic.java8.collectors;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author：Cheng.
 * @date：Created in 23:06 2019/11/10
 */
public class CollectorsTest {

    @Test
    public void collectorsTest1(){
        String strJoin = Stream.of("1","2","3","4")
                .collect(Collectors.joining(",","[","]"));
        System.out.println("strJoin:"+strJoin);
    }

}
