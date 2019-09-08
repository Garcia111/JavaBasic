package com.example.javabasic.util;

import com.alibaba.fastjson.JSON;
import com.example.javabasic.dto.Person;
import com.example.javabasic.dto.PersonInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：Cheng.
 * @date：Created in 21:33 2019/9/7
 */
public class BeanUtilsTest {

    public static void main(String[] args){
        Person p1 = new Person().setName("zhangsan").setAge(11).setHeight(1.87).setWeight(null);
        PersonInfo p2 = new PersonInfo().setName("lisi");
        long tStart = System.currentTimeMillis();
        p2.setAge(p1.getAge());
        p2.setHeight(p1.getHeight());
        p2.setWeight(p1.getWeight());
        long tEnd = System.currentTimeMillis();

        System.out.println("直接使用set get:"+(tEnd-tStart));


        PersonInfo p3 = new PersonInfo().setName("wangwu").setAge(18).setHeight(1.80).setWeight(100);
        //使用BeanUtils实在是太浪费时间了
        long tStart2 = System.currentTimeMillis();
        BeanUtils.copyProperties(p1,p3);
        long tEnd2 = System.currentTimeMillis();

        System.out.println("使用BeanUtils:"+(tEnd2-tStart2));
        System.out.println(JSON.toJSON(p3));

        //通过上例从p1复制到p3可以看到，使用BeanUtils会将p1中的null值也复制到p3中，如何防止这种情况发生呢？
        //需要提取p1中值为null的属性的名称，使用getNullPropertyNames方法可以获取一个对象中值为null的属性的名称集合
        PersonInfo p4 = new PersonInfo().setName("wangwu").setAge(18).setHeight(1.80).setWeight(100);
        copyPropertiesIgnoreNull(p1, p4);
        System.out.println(JSON.toJSON(p4));
    }


    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

}
