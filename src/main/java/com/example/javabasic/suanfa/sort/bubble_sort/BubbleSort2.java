package com.example.javabasic.suanfa.sort.bubble_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.util.Arrays;

/**
 * @author：Cheng.
 * @date：Created in 14:07 2019/12/30
 */
public class BubbleSort2 extends Example {

    public static void sort(Comparable[] array){
        int sortOrder= array.length-1;
        int lastModifiedIndex = 0;

        for(int i=0; i<array.length; i++){
            //是否已经有序
            boolean isSorted = true;
            for(int j=0; j<sortOrder; j++){
                if(less(array[j+1],array[j])){
                    exch(array,j,j+1);
                    //此轮仍然有元素交换，数列没有达到有序
                    isSorted = false;
                    lastModifiedIndex = j;
                }
            }

            if(isSorted){
                break;
            }
            sortOrder = lastModifiedIndex;
        }
    }


    public static void main(String[] args){
        Integer[] arr = new Integer[]{1,3,2,6,5,7,8,9,10,0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
