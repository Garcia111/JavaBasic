package com.example.javabasic.java8;

import com.alibaba.fastjson.JSON;
import com.example.javabasic.dto.Student;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author：Cheng.
 * @date：Created in 14:10 2019/9/6
 */
public class StreamTest {

    @Test
    public void test() {
        //输出["H","e","l","o","W","r","d"]
        String[] words = new String[]{"Hello", "World"};
        List<String> stringList = Stream.of("Hello", "World")
                .flatMap(str -> Arrays.stream(str.split("")))
                .distinct()
                .collect(Collectors.toList());
        System.out.println(JSON.toJSON(stringList));
    }


    List<String[]> eggs = new ArrayList<>();
    // 自增生成组编号
    static int group = 1;
    // 自增生成学生编号
    static int student = 1;

    List<Student> studentList;

    @Before
    public void init() {
        // 第一箱鸡蛋
        eggs.add(new String[]{"鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1", "鸡蛋_1"});
        // 第二箱鸡蛋
        eggs.add(new String[]{"鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2", "鸡蛋_2"});


        studentList = Lists.list(new Student( 1L, "zhangsan", 100),
                new Student(2L, "zhangsan2", 99),
                new Student(3L, "zhangsan3", 300));
    }

    //Map与flatMap的区别
    @Test
    public void map() {
        eggs.stream()
                .map(x -> Arrays.stream(x).map(y -> y.replace("鸡", "煎")))
                .forEach(x -> System.out.println("组" + group++ + ":" + Arrays.toString(x.toArray())));
    }

    @Test
    public void flatMap() {
        //flatMap test
        eggs.stream()
                .flatMap(x -> Arrays.stream(x).map(y -> y.replace("鸡", "煎")))
                .forEach(x -> System.out.println("学生" + student++ + ":" + x));
    }

    @Test
    public void streamTest(){
        //filter
        List<Student> newList = studentList.stream().filter(student -> student.getScore() >= 100).collect(Collectors.toList());
        System.out.println(JSON.toJSON(newList));

        //Stream map to intStream  doubleStream
        int maxScore = studentList.stream().mapToInt(Student::getScore).max().getAsInt();
        System.out.println("max"+maxScore);


        Double average= studentList.stream().mapToInt(Student::getScore).average().getAsDouble();
        System.out.println("average"+average);

        //使用Stream进行排序
        List<Student> sortedByScore = studentList.stream().sorted(Comparator.comparing(Student::getScore)).collect(Collectors.toList());
        System.out.println("sorted List:"+JSON.toJSON(sortedByScore));
    }

}
