package com.example.javabasic.enumration;


import java.util.Random;

/**
 * @author：Cheng.
 * @date：Created in 15:15 2019/8/26
 */
public enum CartoonCharacter  {

    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private static Random rand = new Random(47);

    public static CartoonCharacter next(){
        return values()[rand.nextInt(values().length)];
    }
}
