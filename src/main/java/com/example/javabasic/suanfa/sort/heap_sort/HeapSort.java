package com.example.javabasic.suanfa.sort.heap_sort;

import java.util.Arrays;

/**
 *
 * 使用最大堆进行堆排序
 * @author：Cheng.
 * @date：Created in 14:51 2019/12/17
 */
public class HeapSort {


    public  static void upAdjust(int[] array){
        int childIndex = array.length-1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];

        //跳出循环的条件是temp大于孩子节点
        while (childIndex >0 && temp>array[parentIndex]){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;
        }
        array[childIndex] = temp;

    }


    /***
     * 最大堆堆顶元素下沉，直到其孩子节点均小于父节点
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjust(int[] array, int parentIndex, int length){
        int temp = array[parentIndex];
        int childIndex = 2*parentIndex+1;
        while (childIndex<length){
            if(childIndex+1<length && array[childIndex+1]> array[childIndex]){
                childIndex++;
            }

            if(temp>array[childIndex]){
                break;
            }

            array[parentIndex]= array[childIndex];
            parentIndex = childIndex;
            childIndex = 2 * parentIndex +1;
        }
        array[parentIndex] = temp;
    }



    public static void buildHeap(int[] array){
        for(int i = (array.length/2) -1; i>=0; i--){
            downAdjust(array,i,array.length);
        }
    }


    public static void heapSort(int[] array){
        //首先构建堆
        buildHeap(array);

        System.out.println(Arrays.toString(array));
        for(int i=array.length-1; i>0; i--){
            //将堆尾的元素与第一个元素进行交换
            int temp = array[0];
            array[0] = array[i];
            array[i]=temp;
            //注意这里的参数赋值
            downAdjust(array,0,i);
        }
        //循环删除length-1个堆顶元素，将堆顶元素与堆尾最后一个元素进行替换
    }



    public static void main(String[] args){
        int[] arr = new int[]{1,3,2,6,5,7,8,9,10,0};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }












}
