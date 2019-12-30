package com.example.javabasic.suanfa.sort.count_sort;

import com.example.javabasic.suanfa.sort.Example;


/**
 * @author：Cheng.
 * @date：Created in 16:45 2019/12/30
 */
public class CountSort extends Example {



    public static void main(String[] args) {
        int[] array = new int[]{4,4,6,5,3,2,8,1,7,5,6,0,10};
        int[] sortedArray = sort(array);
        show(sortedArray);
    }


    public static int[] sort(int[] array){
        //确定array中元素的最大值，也即确定辅助数组的长度，辅助数组的长度为max+1，因为array中元素的取值为0~max
        int max = array[0];
        for(int i=1; i<array.length; i++){
            if(max<array[i]){
                max = array[i];
            }
        }

        //将array中的元素映射到countArray数组中，countArray的下标为array中的元素，countArray中元素的值为array中元素出现的次数
        int[] countArray = new int[max+1];
        for(int i=0; i<array.length;i++){
            countArray[array[i]]++;
        }

        //按照countedArray中元素的取值，将其下标输出到sortedArray中
        int index =0;
        int[] sortedArray = new int[array.length];
        for(int i = 0; i<countArray.length; i++){
            for(int j=0; j<countArray[i]; j++){
                sortedArray[index++]=i;
            }
        }
        return sortedArray;
    }

}
