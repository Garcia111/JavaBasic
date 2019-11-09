package com.example.javabasic.java8.default_method_interface;

public interface InterfaceeWithDefaultMethod2 extends InterfaceWithDefaultMethod{

    @Override
    default public void defaultMehod(){
        System.out.println("this is a default method in interface2");
    }

}
