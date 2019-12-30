package com.example.javabasic.suanfa.sort.cock_tail_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.util.Arrays;

/**
 * 优化后的鸡尾酒排序
 * @author：Cheng.
 * @date：Created in 15:47 2019/12/30
 */
public class CockTailSort2 extends Example {

    public static void sort(Comparable[] array){

        //记录左侧最后一次交换的位置
        int lastExchangeIndexLeft=0;
        //记录右侧最后一次交换的位置
        int lastExchangeIndexRight = 0;
        //无序数列的左侧边界
        int sortOrderLeft =0;
        //无序数列的右侧边界
        int sortOrderRight = array.length-1;

        for(int i=0; i<array.length/2; i++){
            boolean isSorted = true;

            //从左向右交换
            for(int j=sortOrderLeft; j<sortOrderRight; j++){
                if(less(array[j+1],array[j])){
                    exch(array,j,j+1);
                    isSorted = false;
                    lastExchangeIndexRight = j;
                }
            }

            sortOrderRight = lastExchangeIndexRight;
            if(isSorted){
                break;
            }

            isSorted = true;
            //从右向左交换
            for(int j = sortOrderRight; j>sortOrderLeft; j--){
                if(less(array[j],array[j-1])){
                    exch(array,j,j-1);
                    isSorted = false;
                    lastExchangeIndexLeft=j;
                }
            }

            sortOrderLeft = lastExchangeIndexLeft;
            if(isSorted){
                break;
            }

        }
    }


    public static void main(String[] args){
        Integer[] arr = new Integer[]{1,3,2,6,5,7,8,9,10,0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
