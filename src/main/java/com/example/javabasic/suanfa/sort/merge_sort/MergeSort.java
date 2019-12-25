package com.example.javabasic.suanfa.sort.merge_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 16:57 2019/12/24
 */
public class MergeSort extends Example {


    public static void sort(Comparable[] array, int start, int end){
        if(start < end){
            //折半拆分为两个小集合，分别进行递归
            int mid = (start+end)/2;
            sort(array,start,mid);
            sort(array,mid+1,end);

            //将两个有序小集合，归并成一个有序的大集合
            merge(array,start,mid,end);
        }
    }


    private static void merge(Comparable[] array, int start, int mid, int end){
        //开辟额外大集合，设置指针
        Comparable[] tempArray = new Comparable[end-start+1];

        int p1=start,p2=mid+1,p=0;

        while (p1<=mid && p2<=end){
            if(less(array[p1],array[p2])){
                tempArray[p++]=array[p1++];
            }else{
                tempArray[p++]=array[p2++];
            }
        }

        while(p1 <= mid){
            //左侧小集合还有剩余，依次放入大集合尾部
            tempArray[p++]=array[p1++];
        }

        while (p2<=end){
            //右侧小集合还有剩余，依次放入大集合尾部
            tempArray[p++]=array[p2++];
        }

        //将大集合的元素复制回原数组
        for(int i=0; i<tempArray.length; i++){
            array[i+start] = tempArray[i];
        }

    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        sort(a,0,a.length-1);
        show(a);
    }








}
