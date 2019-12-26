package com.example.javabasic.suanfa.sort.merge_sort;

import com.example.javabasic.suanfa.sort.Example;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author：Cheng.
 * @date：Created in 10:58 2019/12/26
 */
public class MyDownToUpMergeSort extends Example {

    public static <T extends Comparable<? super T>> void downToUpMergeSort(T[] A){
        @SuppressWarnings("unchecked")
       T[] aux = (T[])Array.newInstance(A.getClass().getComponentType(),A.length);

        //len是指每次归并的长度，i是指归并左子数组的指针，j指归并右子数组的指针，k指归并后的A数组的指针
        //start,mid,end：归并后大数组的start,mid,end
        int len,i,j,k,start,mid,end;

        for(len=1; len<A.length; len = 2*len){
            //每次都要将A中的元素复制到aux，然后归并回A中，然后再将A中元素复制到aux进行下一轮len长度的归并，循环往复，直到len>=A.length
            System.arraycopy(A,0,aux,0,A.length);


            //分块对数组进行归并，每块的起始位置为start, start+2*len, start+4*len.....直到start>=A.length
            for(start = 0; start<A.length; start = start+ 2*len){
                mid = start+len-1;
                end = Math.min(start+2*len-1,A.length-1);

                i=start;
                j=mid+1;
                for(k=start;k<=end;k++){
                    if(i>mid){
                        //aux中【mid+1,end】元素有剩余
                        A[k]=aux[j++];
                    }else if(j>end){
                        A[k] = aux[i++];
                    }else if(aux[i].compareTo(aux[j]) < 0){
                        A[k] = aux[i++];
                    }else {
                        A[k] = aux[j++];
                    }
                }
            }

        }


    }


    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        downToUpMergeSort(a);
        show(a);
    }

}
