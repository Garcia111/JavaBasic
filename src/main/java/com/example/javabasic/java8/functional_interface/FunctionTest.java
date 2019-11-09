package com.example.javabasic.java8.functional_interface;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author：Cheng.
 * @date：Created in 21:56 2019/11/9
 */
public class FunctionTest {


    @Test
    public void functionTest(){
        /**输入s，输出s+1*/
        Function<Integer,Integer> f = s->s++;
        /**输入s，输出s+2*/
        Function<Integer,Integer> g = s->s*2;




        Integer a = g.apply(1);
        System.out.println("a:"+a);
        Integer b = f.apply(a);
        System.out.println("b:"+b);
    }



    @Test
    public void predicateTest(){
        Predicate<String> p = o->o.equals("test");
        Predicate<String> g = o->o.startsWith("t");

        //negate:用于对原来的Predicate进行取反处理，当p.test("test")为True时，调用p.negate().test("test")为false
        Assert.assertFalse(p.negate().test("test"));

        //and针对同一个输入值，多个Predicate均返回True时，会返回True，否则会返回false
        Assert.assertTrue(p.and(g).test("test"));

        //or针对同一个输入值，多个Predicate只要有一个返回True就会返回True，否则会返回false
        Assert.assertTrue(p.or(g).test("ta"));
    }
}
