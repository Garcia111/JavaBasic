package com.example.javabasic.java8.functional_interface;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Java8中的函数式接口Consumer Test
 * @author：Cheng.
 * @date：Created in 10:46 2019/11/8
 */
public class ConsumerTest {


    @Test
    public void test(){
        Consumer f = System.out::println;
        Consumer f2 = n->System.out.println(n+"-F2");

        //指定先执行f的accept方法，然后执行f2的accept方法
        f.andThen(f2).accept("test");
        f.andThen(f).andThen(f).andThen(f).accept("test1d");
    }
}






















