package com.example.javabasic.redis_action.redis_practive_2;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author：Cheng.
 * @date：Created in 11:27 2019/11/24
 */
public class TestBloomFilter {

    private static int size = 1000000;

    //size为布隆过滤器预计插入的值
    //0.001为误判率
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.01);

    public static void main(String[] args){
        for(int i=0;i<size;i++){
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<>(10000);
        //故意取10000个不在过滤器里面的值，看看有多少个会被认为在过滤器里面
        for(int i = size+10000;i<size+20000;i++){
            if(bloomFilter.mightContain(i)){
                list.add(i);
            }
        }
        System.out.println("误判的数量："+list.size());
    }

}
