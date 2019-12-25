package com.example.javabasic.reflection;

/**
 * @author：Cheng.
 * @date：Created in 22:47 2019/11/10
 */
public class GenericToyTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<FancyToy> ftClass = FancyToy.class;

        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();

        /**编译无法通过*/
//        Class<Toy> up2 = ftClass.getSuperclass();

        Object obj = up.newInstance();
        System.out.println(obj.getClass());

    }

}
