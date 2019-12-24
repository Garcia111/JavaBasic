package com.example.javabasic.suanfa.sort.select_sort;

import com.example.javabasic.suanfa.sort.Example;

/**
 * @author：Cheng.
 * @date：Created in 14:04 2019/12/24
 */
public class SelectSort extends Example {



    public static void sort(Comparable[] a) {
        int N = a.length;
        for(int i =0; i<N; i++){
            int min = i;

            for(int j= i+1; j<N;j++){
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{3,6,8,1,23,2,8,4,1};
        sort(a);
        show(a);
    }
}
