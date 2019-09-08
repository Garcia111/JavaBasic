package com.example.javabasic.java8;


import com.alibaba.fastjson.JSON;
import com.example.javabasic.dto.Apple;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author：Cheng.
 * @date：Created in 9:56 2019/9/6
 */
@Slf4j
public class ListToMap {

    private List<Apple> inventory;

    @Before
    public void init(){
        inventory = Lists.newArrayList(new Apple(1,10,"red",new Date()),
                new Apple(2,5,"yellow",new Date()),
                new Apple(3,2,"green",new Date()),
                new Apple(4,15,"green",new Date()),
                new Apple(5,2,"red",new Date()));
    }



    @Test
    public void ListToMap(){
        log.info("=====list转换map,value为对象======");
        Map<Integer, Apple> appleMap = Optional.ofNullable(inventory)
                                                .orElse(Collections.emptyList())
                                                .stream().collect(Collectors.toMap(Apple::getId,apple -> apple));

        log.info(JSON.toJSON(appleMap).toString());
    }


    @Test
    public void listToMapGroup(){
        log.info("=======list转Map分组=======");
       Map<String,List<Apple>> appleGroupByColor = Optional.ofNullable(inventory)
                .orElse(Collections.emptyList())
                .stream().collect(Collectors.groupingBy(apple->apple.getColor()));

        log.info(JSON.toJSON(appleGroupByColor).toString());
    }

    @Test
    public void ListToMap2(){
        log.info("=====list转换为map，value为属性======");
        //使用此方法，当key重复时会发生异常
        Map<Integer,String> appleMap = Optional.ofNullable(inventory)
                                                .orElse(Collections.emptyList())
                                                .stream()
                                                .collect(Collectors.toMap(Apple::getWeight,Apple::getColor));
        log.info(JSON.toJSON(appleMap).toString());
    }

    @Test
    public void listToMap3(){
        log.info("=====list转Map，重复key进行覆盖====");
        Map<Integer,Apple> appleMap = Optional.ofNullable(inventory)
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(Apple::getWeight, Function.identity(),(key1,key2)->key2));
        log.info(JSON.toJSON(appleMap).toString());
    }



}
