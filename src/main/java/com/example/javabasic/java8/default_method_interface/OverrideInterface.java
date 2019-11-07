package com.example.javabasic.java8.default_method_interface;

/**
 * @author：Cheng.
 * @date：Created in 17:32 2019/11/7
 */
public class OverrideInterface implements InterfaceWithDefaultMethod {

    /**
     * 接口的实现类中可以覆盖接口中的默认方法
     */
    @Override
    public void defaultMehod(){
        System.out.println("this is a override method of default method in interface");
    }
}
