package com.example.javabasic.util;

import com.alibaba.fastjson.JSON;
import com.example.javabasic.dto.Department;
import com.example.javabasic.dto.Person;
import com.example.javabasic.dto.PersonInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import java.beans.PropertyDescriptor;
import java.util.*;

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


        //复制过程中忽略empty集合的复制
        Department parentDep = new Department().setId(1).setName("总部门");
        Department childDep1 = new Department().setId(2).setName("子部门1");
        Department childDep2 = new Department().setId(3).setName("子部门2");
        Department childDep3 = new Department().setId(4).setName("子部门3");
        Department parentDep1 = new Department().setId(1).setName("总部门")
                .setChildDeps(Lists.newArrayList(childDep1,childDep2,childDep3));
        copyPropertiesIgnoreNullAndEmpty(parentDep,parentDep1);
        System.out.println(JSON.toJSON(parentDep1));
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


    /**
     * 获取传递的对象中属性为null或者是empty的属性名称的集合
     * @param source
     * @return
     */
    public static String[] getNoValuePropertyNames (Object source) {
        Assert.notNull(source, "传递的参数对象不能为空");
        final BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();

        Set<String> noValuePropertySet = new HashSet<>();
        Arrays.stream(pds).forEach(pd -> {
            Object propertyValue = beanWrapper.getPropertyValue(pd.getName());
            if (Objects.isNull(propertyValue)) {
                noValuePropertySet.add(pd.getName());
            } else {
                if (Iterable.class.isAssignableFrom(propertyValue.getClass())) {
                    Iterable iterable = (Iterable) propertyValue;
                    Iterator iterator = iterable.iterator();
                    if (!iterator.hasNext()) noValuePropertySet.add(pd.getName());
                }
                if (Map.class.isAssignableFrom(propertyValue.getClass())) {
                    Map map = (Map) propertyValue;
                    if (map.isEmpty()) noValuePropertySet.add(pd.getName());
                }
            }
        });
        String[] result = new String[noValuePropertySet.size()];
        return noValuePropertySet.toArray(result);
    }


    public static void copyPropertiesIgnoreNull(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }


    public static void copyPropertiesIgnoreNullAndEmpty(Object src, Object target){
        BeanUtils.copyProperties(src, target, getNoValuePropertyNames(src));
    }
}
