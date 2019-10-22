package com.example.javabasic.enumration;

import static com.example.javabasic.enumration.CartoonCharacter.BOB;
import static com.example.javabasic.enumration.CartoonCharacter.next;

/**
 * @author：Cheng.
 * @date：Created in 15:25 2019/8/26
 */
public class EnumImplementation {
    public static <T> void printNext(CartoonCharacter rg){
        System.out.println(next()+", ");
    }

    public static void main(String[] args){
        CartoonCharacter cc = BOB;
        for(int i = 0; i<10;i++){
            printNext(cc);
        }
    }

}
