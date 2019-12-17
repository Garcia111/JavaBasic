package com.example.javabasic.suanfa.binary_heap;

import java.util.Arrays;

/**
 * 最小堆的上浮和下沉操作
 * @author：Cheng.
 * @date：Created in 10:51 2019/12/17
 */
public class MyMinimizeHeapOperator {

    /**
     * 数组的结尾新插入一个值时，对新插入的值进行上浮操作
     * @param array
     */
    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;

        int temp = array[childIndex];

        /**
         * 循环的截止条件是childIndex =0，或者 array[parentIndex]<temp
         */
        while (childIndex>0 && temp<array[parentIndex]){
            //进行单项赋值，将parent赋值给child
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;
        }

        array[childIndex]= temp;
    }


    /**
     * 对最小堆的元素进行下沉调整
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array,int parentIndex,int length){
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex<length){
            if(childIndex+1<length && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            //循环的截止条件是temp<array[childIndex]
           if (temp<=array[childIndex]){
                break;
            }
            //未达到循环截止条件，则将parent节点进行下沉，
            //无需真正交换，单向赋值即可
            array[parentIndex]=array[childIndex];
            parentIndex = childIndex;
            childIndex = 2* parentIndex+1;
        }
        array[parentIndex]= temp;
    }

    /**
     * 构建二叉堆
     * @param array
     */
    public static void  buildHeap(int[] array){

        //下沉从第一个非叶子节点开始
        for(int i= (array.length/2);i>=0;i--){
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args){
        int[] array = new int[]{1,3,2,4,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));

        int[] array2 = new int[]{7,1,3,10,5,2,8,9,6};
        buildHeap(array2);
        System.out.println(Arrays.toString(array2));


    }


}
