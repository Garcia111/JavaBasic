package com.example.javabasic.java8.Stream;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author：Cheng.
 * @date：Created in 9:09 2019/11/8
 */
public class StreamTest {

    private List<String>  stringList;

    @Before
    public void init(){
        stringList = Lists.newArrayList("Monica","Chandler","Rachel","Ross","Joey","Phebi");
    }

    @Test
    public void streamTest(){
//        filter(stringList);
//        sort(stringList);
//        map(stringList);
//        match(stringList);
//        count(stringList);
//        reduce(stringList);
        flatMap();
    }


    public void filter(List<String> list){
        list.stream().filter(s->s.startsWith("R")).forEach(System.out::println);
    }

    public void sort(List<String> list){
        list.stream().sorted().forEach(System.out::println);
    }

    public void map(List<String> list){
        //a.compareTo(b) 正序输出：从小到大
        //b.compareTo(a) 倒叙输出：从大到小
        list.stream().map(String::toUpperCase).sorted((a,b)->a.compareTo(b)).forEach(System.out::println);
    }

    public void match(List<String> list){
        boolean anyStartsWithR = list.stream().anyMatch((s)->s.startsWith("R"));
        System.out.println("anyMatch: "+anyStartsWithR);

        boolean allStartWithR = list.stream().allMatch((s)->s.startsWith("R"));
        System.out.println("allMatch: "+allStartWithR);


        boolean noneStartWithR = list.stream().noneMatch((s)->s.startsWith("R"));
        System.out.println("noneMatch: "+noneStartWithR);
    }


    public void count(List<String> list){
        Long count = list.stream().filter(s->s.startsWith("R")).count();
        System.out.println("count: "+count);
    }





    /**
     *元素的一对多的转换：对原Stream中的所有元素使用传入的Function进行处理，每个元素经过处理后生成一个含有多个元素的
     * Stream对象，然后将返回所有的Stream对象中的所有元素组成一个统一的Stream并返回。
     * 我们可以将flatMap理解为各个小河中的水滴归入大海，hhhh
     */
    @Test
    public void flatMap(){
        Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
        s.flatMap(n -> Stream.of(n.split(""))).forEach(System.out::println);
    }




}













