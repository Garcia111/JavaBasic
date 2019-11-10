package com.example.javabasic.java8.parallel_stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 13:28 2019/11/10
 */
public class ParallelStreamTest {

    @Test
    public void parallelStreamTest(){
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11);
        numbers.parallelStream().forEach(num->System.out.println(num));
        System.out.println("===============");
        numbers.parallelStream().forEach(num->System.out.println(Thread.currentThread().getName()+">>"+num));
    }


    @Test
    public void streamTest(){
        try{
            List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9);
            numbers.parallelStream().forEach(num->{
                System.out.println("第一次请求并行："+Thread.currentThread().getName()+">>"+num);
                try{
                    Thread.sleep(50001);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
