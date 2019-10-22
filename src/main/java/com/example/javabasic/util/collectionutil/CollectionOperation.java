package com.example.javabasic.util.collectionutil;


import com.alibaba.fastjson.JSON;
import com.example.javabasic.dto.Student;
import org.apache.commons.collections4.CollectionUtils;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author：Cheng. Apache CollectionUtils工具类
 * @date：Created in 16:13 2019/9/6
 */
public class CollectionOperation {

    /**
     * 求两个集合的差集，返回集合为from-db
     * @return
     */
    @Test
    public void  chaJi(){
        List<Integer> db = Lists.newArrayList(1,2,3);
        List<Integer> from = Lists.newArrayList(1,2,3,4,5);
        System.out.println(JSON.toJSON(CollectionUtils.subtract(from,db)));
    }

    /**
     * 过滤重复元素
     */
    @Test
    public void filter(){
        List<String> fromList = Lists.newArrayList("a","b","c","c");
        Set<String> setB = Sets.newHashSet(fromList);
        System.out.println(CollectionUtils.disjunction(fromList,setB));
    }


    /**
     * 求交集
     */
    @Test
    public void intersection(){
        List<String> a = Lists.newArrayList("a","b","c","d");
        List<String> b = Lists.newArrayList("a","b","b","d");
        System.out.println(CollectionUtils.intersection(a,b));
    }

    /**
     * 判断集合是否相等
     */
    @Test
    public void isEqualCollection(){
        List<String> fromList = Lists.newArrayList("a","b","c","c");
        List<String> fromList2 = Lists.newArrayList("a","c","c","b");
        System.out.println(CollectionUtils.isEqualCollection(fromList, fromList2));


        List<Student> studentList1= Lists.newArrayList(new Student((long) 1,"zhangsan",1),
                new Student((long) 2,"zhangsan",2),
                new Student((long) 3,"zhangsan",3));

        List<Student> studentList2= Lists.newArrayList(new Student((long) 1,"zhangsan",1),
                new Student((long) 2,"zhangsan",2),
                new Student((long) 3,"zhangsan",3));

        System.out.println(CollectionUtils.isEqualCollection(studentList1, studentList2));
    }


    /**
     * 判断两个集合是否存在交集
     */
    @Test
    public void containAny(){
        List<String> list1 = Lists.newArrayList("a","b","c","c");
        List<String> list2 = Lists.newArrayList("1","2","3","b");
        System.out.println(CollectionUtils.containsAny(list1,list2));
    }

}
