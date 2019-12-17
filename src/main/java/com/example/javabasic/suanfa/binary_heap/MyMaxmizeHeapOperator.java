package com.example.javabasic.suanfa.binary_heap;

import java.util.Arrays;

/**
 * 最大堆的上浮和下沉操作
 * @author：Cheng.
 * @date：Created in 11:19 2019/12/17
 */
public class MyMaxmizeHeapOperator {

    /**
     * 最大堆的上浮调整，即要求父节点大于孩子节点
     * @param array
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];

        //循环的截止条件是childIndex =0或者 temp<=array{parentIndex}
        while (childIndex>0 && temp>array[parentIndex]){
            //单向赋值
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex =(childIndex-1)/2;
        }
        array[childIndex] = temp;
    }


    /**
     * 最大堆的下沉调整
     * 判断parent是不是大于其孩子节点，不满足则对parent进行下沉
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array,int parentIndex,int length){
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex<length){
            if(childIndex+1<length && array[childIndex+1]>array[childIndex]){
                System.out.println("childIndex+1:"+array[childIndex+1]+",childIndex:");
                childIndex++;
            }
            if(temp>=array[childIndex]){
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = 2*parentIndex+1;
        }
        array[parentIndex]= temp;
    }


    public static void buildHeap(int[] array){
        for(int i = array.length/2 -1;i>=0; i--){
            downAdjust(array,i,array.length);
        }
    }


    public static void main(String[] args){
        int[] array = new int[]{10,9,8,7,6,5,4,3,2,12};
        upAdjust(array);
        System.out.println(Arrays.toString(array));



        int[] array2 = new int[]{7,1,3,10,5,2,8,9,6};
        buildHeap(array2);
        System.out.println(Arrays.toString(array2));


    }

}
