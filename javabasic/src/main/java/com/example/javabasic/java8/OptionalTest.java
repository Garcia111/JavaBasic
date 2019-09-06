package com.example.javabasic.java8;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author：Cheng.
 * @date：Created in 11:03 2019/9/6
 */
@Slf4j
public class OptionalTest {


    //JDK8中的Optional类用来防止NPE问题----Null Pointer Exception



    @Test
    public void OptionalTest() {
        String str = null;
        //Optional的ofNullable方法可以接收null值
        String fruit = Optional.ofNullable(str).orElse("Banana");
        log.info("fruit:" + fruit);

        //Optional的of方法不能接收null值
        String haha = Optional.of(fruit).orElse("balabala");

        //Optional的get方法获取value值
        //Optional中ifPresent的使用
        String s = "hahahaha";
        List<String> list = new ArrayList<>();
        Optional.of(s).ifPresent(s1 -> list.add(s1.replace('a', 'e')));
        System.out.println(JSON.toJSON(list));

        //Optional的orElseGet  orELse  orElseThrow



    }

    @Test
    public void OptionalTest2() {
        List<Optional<String>> list = Arrays.asList(
                Optional.empty(),
                Optional.of("A"),
                Optional.empty(),
                Optional.of("B"));

        //if optional is non-empty, get the value in stream, otherwise return empty
        List<String> filteredList = list.stream()
                .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
                .collect(Collectors.toList());

        System.out.println(filteredList);
    }
}
