package com.example.javabasic.suanfa.sort.bubble_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.util.Arrays;


/**
 * 冒泡排序
 */
public class BubbleSort extends Example {

    public static void bubbleSort(Comparable[] array){
        for(int i=0; i< array.length; i++){
            for(int j=0; j<array.length-1-i; j++){
                if(less(array[j+1],array[j])){
                    exch(array,j,j+1);
                }
            }
        }
    }


    public static void main(String[] args){
        Integer[] arr = new Integer[]{1,3,2,6,5,7,8,9,10,0};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
