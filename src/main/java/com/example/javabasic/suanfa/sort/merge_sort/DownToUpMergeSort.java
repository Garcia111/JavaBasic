package com.example.javabasic.suanfa.sort.merge_sort;


import java.lang.reflect.Array;

/**
 * @author：Cheng.
 * @date：Created in 10:23 2019/12/26
 */
public class DownToUpMergeSort {

    //自底向上归并排序
    public static <T extends Comparable<? super T>> void MergeSortDownToUp(T[] A){
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) Array.newInstance(A.getClass().getComponentType(), A.length);
        int len,i,j,k,start,mid,end;
        //len表示归并子数组的长度，1表示，一个一个的归并，归并后的长度为2,2表示两个两个的归并，归并后的长度为4,以此类推
        for(len = 1; len < A.length; len = 2*len){
            //复制到辅助数组中
            System.arraycopy(A, 0, aux, 0, A.length);
            //按照len的长度归并回A数组，归并后长度翻倍
            for(start = 0; start < A.length; start = start+2*len){
                mid = start + len - 1;
                //对于数组长度不满足2的x次幂的数组，mid可能会大于end
                end = Math.min(start + 2*len - 1, A.length-1);
                i = start;
                //mid大于end时,j必然大于end,所以不会引起越界访问
                j = mid+1;

                //[start,mid] [mid+1, end],归并
                for(k = start; k <= end; k++){
                    if(i > mid){
                        A[k] = aux[j++];
                    }else
                    if(j > end){
                        A[k] = aux[i++];
                    }else
                    if(aux[i].compareTo(aux[j]) < 0){
                        A[k] = aux[i++];
                    }else{
                        A[k] = aux[j++];
                    }
                }
            }
        }
    }

}
