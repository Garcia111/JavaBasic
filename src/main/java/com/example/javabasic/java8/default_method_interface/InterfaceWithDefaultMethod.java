package com.example.javabasic.java8.default_method_interface;

/**
 * @author：Cheng.
 * @date：Created in 17:30 2019/11/7
 */
public interface InterfaceWithDefaultMethod {

    default public void defaultMehod(){
        System.out.println("this is a default method in interface");
    }

}
