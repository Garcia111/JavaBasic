package com.example.javabasic.suanfa.sort.merge_sort;

import java.lang.reflect.Array;

import static com.example.javabasic.suanfa.sort.Example.show;

/**
 * @author：Cheng.
 * @date：Created in 9:35 2019/12/26
 */
public class UpToDownMergeSort {

    public static <T extends Comparable<? super T>>
    void MergeSortUpToDown(T[] A){
//        System.out.println(A.getClass().getComponentType());
        @SuppressWarnings("unchecked")
        //创建合并两个有序序列的辅助数组
        T[] aux = (T[]) Array.newInstance(A.getClass().getComponentType(), A.length);
        mergeSortUpToDown0(A, aux, 0, A.length-1);
    }


    /**
     * 其实这里的短发步骤和MergeSort.jsvs中的算法思想是一样的，
     * @param A
     * @param aux
     * @param start
     * @param end
     * @param <T>
     */
    public static <T extends Comparable<? super T>>
    void mergeSortUpToDown0(T[] A, T[] aux, int start, int end){
        if(start >= end){
            return;
        }
        int mid = (start+end)/2;
        mergeSortUpToDown0(A, aux, start, mid);
        mergeSortUpToDown0(A, aux, mid+1, end);
        //复制到辅助数组aux中,此时[start,mid] [mid+1, end]两个子数组已经有序
        System.arraycopy(A, start, aux, start, end - start + 1);
        //然后将aux中的数组归并回A中
        int i = start, j = mid+1, k;
        for(k = start; k <= end; k++){
            if(i > mid){
                //此时i=mid+1，[start,mid]中的元素已经取完，[mid+1, end]中的元素有剩余
                A[k] = aux[j++];
            }else
            if(j > end){
                //此时j=end+1，[mid+1, end]中的元素已经取完，[start,mid]中的元素有剩余
                A[k] = aux[i++];
            }else
                //[start,mid]中有元素小于[mid+1, end]中的元素，将i指向的元素存入数组A
            if(aux[i].compareTo(aux[j]) <= 0){
                A[k] = aux[i++];
            }else{
                //[mid+1, end]中有元素小于[start,mid]中的元素，将j指向的元素存入数组A
                A[k] = aux[j++];
            }
        }
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        MergeSortUpToDown(a);
        show(a);
    }

}
