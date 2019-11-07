package com.example.javabasic.java8.functional_interface;


/**
 * 函数式接口
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
public interface Converter<F,T> {

    T convert(F from);
}
