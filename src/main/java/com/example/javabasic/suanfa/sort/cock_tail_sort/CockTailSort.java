package com.example.javabasic.suanfa.sort.cock_tail_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.util.Arrays;

/**
 * 鸡尾酒排序
 * @author：Cheng.
 * @date：Created in 14:42 2019/12/30
 */
public class CockTailSort extends Example {

    public static void sort(Comparable[] array){
        //注意这里，因为是双向排序，所以i只用取array.length/2即可
        for(int i=0; i<array.length/2; i++){
            //当前数列是否已经有序了
            boolean isSorted = true;
            //从左向右排序
            for(int j=i; j<array.length-1; j++){
                if(less(array[j+1],array[j])){
                    exch(array,j,j+1);

                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
            }

            if(isSorted){
                break;
            }
            //偶数轮之前，重新标记为true，开始从右向左排序
            isSorted = true;
            //这里j的初始值变为array.length-2-i，是为了排除数列右侧已经排好序的元素
            for(int j = array.length-2-i; j>i; j--){
                if(less(array[j],array[j-1])){
                    exch(array,j,j-1);
                    isSorted = false;
                }
            }

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
