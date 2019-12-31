package com.example.javabasic.suanfa.sort.count_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 10:07 2019/12/31
 */
public class CountSort2 extends Example {


    public static int[] sort(int[] array){
        int max = array[0];
        int min = array[0];
        for(int i=0; i<array.length; i++){
            if(array[i]<min){
                min = array[i];
            }
            if(array[i]>max){
                max = array[i];
            }
        }

        int len = max-min+1;

        int[] countArray = new int[len];
        for(int i=0; i<array.length; i++){
            countArray[array[i]-min]++;
        }

        //统计数组做变性，后面的元素等于前面的元素之和
        int sum = 0;
        for(int i = 0; i<countArray.length; i++){
            sum += countArray[i];
            countArray[i] = sum;
        }

        //倒叙遍历原始数列，从统计数组找到正确的位置，输出到结果数组
        int[] sortedArray = new int[array.length];
        for(int i = array.length-1; i>=0; i--){
            sortedArray[countArray[array[i]-min]-1] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;

    }


    public static void main(String[] args) {
        int[] array = new int[] {95,94,91,98,99,90,99,93,91,92};
        int[] sortedAray = sort(array);
        show(sortedAray);
    }

}
