package com.example.javabasic.collection;


import org.apache.commons.collections4.ListUtils;
import org.assertj.core.util.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 16:04 2019/10/27
 */
public class RemoveDuplicate {

//    public static void main(String[] args){
//        List list = Lists.newArrayList(1,2,3,4,5,1);
//        System.out.println(list);
//
//        Set set = new HashSet(list);
//        System.out.println(set);
//    }




    public static void main(String[] args){
        Actor rachel = new Actor().setName("Rachel").setSex("female");
        Actor ross = new Actor().setName("Ross").setSex("male");
        Actor monica = new Actor().setName("Monica").setSex("female");
        Actor rachel2 = rachel;
        Actor rache3 = new Actor().setName("Rachel").setSex("female");
        List actorList = Lists.newArrayList(rachel,ross,monica,rachel2,rache3);
        System.out.println(actorList);


        Set actorSet = new HashSet(actorList);
        System.out.println(actorSet);
    }
}
