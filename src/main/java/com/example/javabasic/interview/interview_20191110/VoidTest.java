package com.example.javabasic.interview.interview_20191110;

import java.lang.reflect.Method;

/**
 * @author：Cheng.
 * @date：Created in 16:53 2019/11/10
 */
public class VoidTest {

    public void print(String v){}


    public static void main(String[] args){
        for(Method method: VoidTest.class.getMethods()){
            if(method.getAnnotatedReturnType().equals(Void.TYPE)){
                System.out.println(method.getName());
            }
        }
    }
}
