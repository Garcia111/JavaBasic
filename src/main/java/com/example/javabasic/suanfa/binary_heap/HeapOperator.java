package com.example.javabasic.suanfa.binary_heap;

import java.util.Arrays;

/**
 * @author：Cheng.
 * @date：Created in 9:53 2019/12/17
 */
public class HeapOperator {

    public static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;

        int temp = array[childIndex];

        while (childIndex>0 && temp<array[parentIndex]){
            //代码中此处进行了优化，就是父节点与孩子节点进行连续交换时，并不一定要真正的交换，只需要先把交换一方
            //的值存入temp，做单项覆盖，循环结束时，再将temp的值存入交换后的最终位置
            array[childIndex]= array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex] = temp;
    }


    public static void downAdjust(int[] array, int parentIndex, int length){
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex +1;

        while (childIndex < length){
            if(childIndex+1 < length && array[childIndex+1]<array[childIndex]){
                childIndex++;
            }
            //最小堆
            if(temp<= array[childIndex]){
                break;
            }


            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex*2+1;
        }
        array[parentIndex]=temp;
    }



    public static void buildHeap(int[] array){
        for(int i = array.length/2;i>=0;i--){
            downAdjust(array,i,array.length-1);
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
