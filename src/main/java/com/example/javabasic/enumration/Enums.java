package com.example.javabasic.enumration;

import java.util.Random;

/**
 * @author：Cheng.
 * @date：Created in 16:36 2019/8/26
 */
public class Enums {

    private static Random random = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values){
        return values[random.nextInt(values.length)];
    }
}
